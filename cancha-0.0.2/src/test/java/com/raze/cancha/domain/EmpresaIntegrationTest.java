package com.raze.cancha.domain;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@RooIntegrationTest(entity = Empresa.class)
public class EmpresaIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    EmpresaDataOnDemand dod;

	@Test
    public void testCountEmpresas() {
        Assert.assertNotNull("Data on demand for 'Empresa' failed to initialize correctly", dod.getRandomEmpresa());
        long count = Empresa.countEmpresas();
        Assert.assertTrue("Counter for 'Empresa' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindEmpresa() {
        Empresa obj = dod.getRandomEmpresa();
        Assert.assertNotNull("Data on demand for 'Empresa' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Empresa' failed to provide an identifier", id);
        obj = Empresa.findEmpresa(id);
        Assert.assertNotNull("Find method for 'Empresa' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Empresa' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllEmpresas() {
        Assert.assertNotNull("Data on demand for 'Empresa' failed to initialize correctly", dod.getRandomEmpresa());
        long count = Empresa.countEmpresas();
        Assert.assertTrue("Too expensive to perform a find all test for 'Empresa', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Empresa> result = Empresa.findAllEmpresas();
        Assert.assertNotNull("Find all method for 'Empresa' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Empresa' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEmpresaEntries() {
        Assert.assertNotNull("Data on demand for 'Empresa' failed to initialize correctly", dod.getRandomEmpresa());
        long count = Empresa.countEmpresas();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Empresa> result = Empresa.findEmpresaEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Empresa' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Empresa' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Empresa obj = dod.getRandomEmpresa();
        Assert.assertNotNull("Data on demand for 'Empresa' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Empresa' failed to provide an identifier", id);
        obj = Empresa.findEmpresa(id);
        Assert.assertNotNull("Find method for 'Empresa' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyEmpresa(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Empresa' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Empresa obj = dod.getRandomEmpresa();
        Assert.assertNotNull("Data on demand for 'Empresa' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Empresa' failed to provide an identifier", id);
        obj = Empresa.findEmpresa(id);
        boolean modified =  dod.modifyEmpresa(obj);
        Integer currentVersion = obj.getVersion();
        Empresa merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Empresa' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Empresa' failed to initialize correctly", dod.getRandomEmpresa());
        Empresa obj = dod.getNewTransientEmpresa(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Empresa' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Empresa' identifier to be null", obj.getId());
        try {
            obj.persist();
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        obj.flush();
        Assert.assertNotNull("Expected 'Empresa' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Empresa obj = dod.getRandomEmpresa();
        Assert.assertNotNull("Data on demand for 'Empresa' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Empresa' failed to provide an identifier", id);
        obj = Empresa.findEmpresa(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Empresa' with identifier '" + id + "'", Empresa.findEmpresa(id));
    }
}

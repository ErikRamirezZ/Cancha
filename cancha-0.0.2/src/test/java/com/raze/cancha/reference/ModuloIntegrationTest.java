package com.raze.cancha.reference;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
@RooIntegrationTest(entity = Modulo.class)
public class ModuloIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ModuloDataOnDemand dod;

	@Test
    public void testCountModuloes() {
        Assert.assertNotNull("Data on demand for 'Modulo' failed to initialize correctly", dod.getRandomModulo());
        long count = Modulo.countModuloes();
        Assert.assertTrue("Counter for 'Modulo' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindModulo() {
        Modulo obj = dod.getRandomModulo();
        Assert.assertNotNull("Data on demand for 'Modulo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Modulo' failed to provide an identifier", id);
        obj = Modulo.findModulo(id);
        Assert.assertNotNull("Find method for 'Modulo' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Modulo' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllModuloes() {
        Assert.assertNotNull("Data on demand for 'Modulo' failed to initialize correctly", dod.getRandomModulo());
        long count = Modulo.countModuloes();
        Assert.assertTrue("Too expensive to perform a find all test for 'Modulo', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Modulo> result = Modulo.findAllModuloes();
        Assert.assertNotNull("Find all method for 'Modulo' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Modulo' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindModuloEntries() {
        Assert.assertNotNull("Data on demand for 'Modulo' failed to initialize correctly", dod.getRandomModulo());
        long count = Modulo.countModuloes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Modulo> result = Modulo.findModuloEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Modulo' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Modulo' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Modulo obj = dod.getRandomModulo();
        Assert.assertNotNull("Data on demand for 'Modulo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Modulo' failed to provide an identifier", id);
        obj = Modulo.findModulo(id);
        Assert.assertNotNull("Find method for 'Modulo' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyModulo(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Modulo' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Modulo obj = dod.getRandomModulo();
        Assert.assertNotNull("Data on demand for 'Modulo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Modulo' failed to provide an identifier", id);
        obj = Modulo.findModulo(id);
        boolean modified =  dod.modifyModulo(obj);
        Integer currentVersion = obj.getVersion();
        Modulo merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Modulo' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Modulo' failed to initialize correctly", dod.getRandomModulo());
        Modulo obj = dod.getNewTransientModulo(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Modulo' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Modulo' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Modulo' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Modulo obj = dod.getRandomModulo();
        Assert.assertNotNull("Data on demand for 'Modulo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Modulo' failed to provide an identifier", id);
        obj = Modulo.findModulo(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Modulo' with identifier '" + id + "'", Modulo.findModulo(id));
    }
}

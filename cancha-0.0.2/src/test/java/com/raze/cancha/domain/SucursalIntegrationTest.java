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
@RooIntegrationTest(entity = Sucursal.class)
public class SucursalIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    SucursalDataOnDemand dod;

	@Test
    public void testCountSucursals() {
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to initialize correctly", dod.getRandomSucursal());
        long count = Sucursal.countSucursals();
        Assert.assertTrue("Counter for 'Sucursal' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindSucursal() {
        Sucursal obj = dod.getRandomSucursal();
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to provide an identifier", id);
        obj = Sucursal.findSucursal(id);
        Assert.assertNotNull("Find method for 'Sucursal' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Sucursal' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllSucursals() {
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to initialize correctly", dod.getRandomSucursal());
        long count = Sucursal.countSucursals();
        Assert.assertTrue("Too expensive to perform a find all test for 'Sucursal', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Sucursal> result = Sucursal.findAllSucursals();
        Assert.assertNotNull("Find all method for 'Sucursal' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Sucursal' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindSucursalEntries() {
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to initialize correctly", dod.getRandomSucursal());
        long count = Sucursal.countSucursals();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Sucursal> result = Sucursal.findSucursalEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Sucursal' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Sucursal' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Sucursal obj = dod.getRandomSucursal();
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to provide an identifier", id);
        obj = Sucursal.findSucursal(id);
        Assert.assertNotNull("Find method for 'Sucursal' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySucursal(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Sucursal' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Sucursal obj = dod.getRandomSucursal();
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to provide an identifier", id);
        obj = Sucursal.findSucursal(id);
        boolean modified =  dod.modifySucursal(obj);
        Integer currentVersion = obj.getVersion();
        Sucursal merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Sucursal' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to initialize correctly", dod.getRandomSucursal());
        Sucursal obj = dod.getNewTransientSucursal(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Sucursal' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Sucursal' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Sucursal obj = dod.getRandomSucursal();
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Sucursal' failed to provide an identifier", id);
        obj = Sucursal.findSucursal(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Sucursal' with identifier '" + id + "'", Sucursal.findSucursal(id));
    }
}

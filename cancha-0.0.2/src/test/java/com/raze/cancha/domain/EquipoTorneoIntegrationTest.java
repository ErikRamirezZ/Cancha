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
@RooIntegrationTest(entity = EquipoTorneo.class)
public class EquipoTorneoIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    EquipoTorneoDataOnDemand dod;

	@Test
    public void testCountEquipoTorneos() {
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to initialize correctly", dod.getRandomEquipoTorneo());
        long count = EquipoTorneo.countEquipoTorneos();
        Assert.assertTrue("Counter for 'EquipoTorneo' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindEquipoTorneo() {
        EquipoTorneo obj = dod.getRandomEquipoTorneo();
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to provide an identifier", id);
        obj = EquipoTorneo.findEquipoTorneo(id);
        Assert.assertNotNull("Find method for 'EquipoTorneo' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'EquipoTorneo' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllEquipoTorneos() {
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to initialize correctly", dod.getRandomEquipoTorneo());
        long count = EquipoTorneo.countEquipoTorneos();
        Assert.assertTrue("Too expensive to perform a find all test for 'EquipoTorneo', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<EquipoTorneo> result = EquipoTorneo.findAllEquipoTorneos();
        Assert.assertNotNull("Find all method for 'EquipoTorneo' illegally returned null", result);
        Assert.assertTrue("Find all method for 'EquipoTorneo' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEquipoTorneoEntries() {
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to initialize correctly", dod.getRandomEquipoTorneo());
        long count = EquipoTorneo.countEquipoTorneos();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<EquipoTorneo> result = EquipoTorneo.findEquipoTorneoEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'EquipoTorneo' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'EquipoTorneo' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        EquipoTorneo obj = dod.getRandomEquipoTorneo();
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to provide an identifier", id);
        obj = EquipoTorneo.findEquipoTorneo(id);
        Assert.assertNotNull("Find method for 'EquipoTorneo' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyEquipoTorneo(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'EquipoTorneo' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        EquipoTorneo obj = dod.getRandomEquipoTorneo();
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to provide an identifier", id);
        obj = EquipoTorneo.findEquipoTorneo(id);
        boolean modified =  dod.modifyEquipoTorneo(obj);
        Integer currentVersion = obj.getVersion();
        EquipoTorneo merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'EquipoTorneo' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to initialize correctly", dod.getRandomEquipoTorneo());
        EquipoTorneo obj = dod.getNewTransientEquipoTorneo(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'EquipoTorneo' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'EquipoTorneo' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        EquipoTorneo obj = dod.getRandomEquipoTorneo();
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EquipoTorneo' failed to provide an identifier", id);
        obj = EquipoTorneo.findEquipoTorneo(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'EquipoTorneo' with identifier '" + id + "'", EquipoTorneo.findEquipoTorneo(id));
    }
}

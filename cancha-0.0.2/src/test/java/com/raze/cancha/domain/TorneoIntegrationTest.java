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
@RooIntegrationTest(entity = Torneo.class)
public class TorneoIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    TorneoDataOnDemand dod;

	@Test
    public void testCountTorneos() {
        Assert.assertNotNull("Data on demand for 'Torneo' failed to initialize correctly", dod.getRandomTorneo());
        long count = Torneo.countTorneos();
        Assert.assertTrue("Counter for 'Torneo' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindTorneo() {
        Torneo obj = dod.getRandomTorneo();
        Assert.assertNotNull("Data on demand for 'Torneo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Torneo' failed to provide an identifier", id);
        obj = Torneo.findTorneo(id);
        Assert.assertNotNull("Find method for 'Torneo' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Torneo' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllTorneos() {
        Assert.assertNotNull("Data on demand for 'Torneo' failed to initialize correctly", dod.getRandomTorneo());
        long count = Torneo.countTorneos();
        Assert.assertTrue("Too expensive to perform a find all test for 'Torneo', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Torneo> result = Torneo.findAllTorneos();
        Assert.assertNotNull("Find all method for 'Torneo' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Torneo' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindTorneoEntries() {
        Assert.assertNotNull("Data on demand for 'Torneo' failed to initialize correctly", dod.getRandomTorneo());
        long count = Torneo.countTorneos();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Torneo> result = Torneo.findTorneoEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Torneo' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Torneo' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Torneo obj = dod.getRandomTorneo();
        Assert.assertNotNull("Data on demand for 'Torneo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Torneo' failed to provide an identifier", id);
        obj = Torneo.findTorneo(id);
        Assert.assertNotNull("Find method for 'Torneo' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyTorneo(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Torneo' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Torneo obj = dod.getRandomTorneo();
        Assert.assertNotNull("Data on demand for 'Torneo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Torneo' failed to provide an identifier", id);
        obj = Torneo.findTorneo(id);
        boolean modified =  dod.modifyTorneo(obj);
        Integer currentVersion = obj.getVersion();
        Torneo merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Torneo' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Torneo' failed to initialize correctly", dod.getRandomTorneo());
        Torneo obj = dod.getNewTransientTorneo(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Torneo' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Torneo' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Torneo' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Torneo obj = dod.getRandomTorneo();
        Assert.assertNotNull("Data on demand for 'Torneo' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Torneo' failed to provide an identifier", id);
        obj = Torneo.findTorneo(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Torneo' with identifier '" + id + "'", Torneo.findTorneo(id));
    }
}

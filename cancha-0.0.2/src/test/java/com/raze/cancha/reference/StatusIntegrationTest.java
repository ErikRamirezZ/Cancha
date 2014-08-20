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
@RooIntegrationTest(entity = Status.class)
public class StatusIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    StatusDataOnDemand dod;

	@Test
    public void testCountStatuses() {
        Assert.assertNotNull("Data on demand for 'Status' failed to initialize correctly", dod.getRandomStatus());
        long count = Status.countStatuses();
        Assert.assertTrue("Counter for 'Status' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindStatus() {
        Status obj = dod.getRandomStatus();
        Assert.assertNotNull("Data on demand for 'Status' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Status' failed to provide an identifier", id);
        obj = Status.findStatus(id);
        Assert.assertNotNull("Find method for 'Status' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Status' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllStatuses() {
        Assert.assertNotNull("Data on demand for 'Status' failed to initialize correctly", dod.getRandomStatus());
        long count = Status.countStatuses();
        Assert.assertTrue("Too expensive to perform a find all test for 'Status', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Status> result = Status.findAllStatuses();
        Assert.assertNotNull("Find all method for 'Status' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Status' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindStatusEntries() {
        Assert.assertNotNull("Data on demand for 'Status' failed to initialize correctly", dod.getRandomStatus());
        long count = Status.countStatuses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Status> result = Status.findStatusEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Status' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Status' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Status obj = dod.getRandomStatus();
        Assert.assertNotNull("Data on demand for 'Status' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Status' failed to provide an identifier", id);
        obj = Status.findStatus(id);
        Assert.assertNotNull("Find method for 'Status' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatus(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Status' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Status obj = dod.getRandomStatus();
        Assert.assertNotNull("Data on demand for 'Status' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Status' failed to provide an identifier", id);
        obj = Status.findStatus(id);
        boolean modified =  dod.modifyStatus(obj);
        Integer currentVersion = obj.getVersion();
        Status merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Status' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Status' failed to initialize correctly", dod.getRandomStatus());
        Status obj = dod.getNewTransientStatus(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Status' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Status' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Status' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Status obj = dod.getRandomStatus();
        Assert.assertNotNull("Data on demand for 'Status' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Status' failed to provide an identifier", id);
        obj = Status.findStatus(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Status' with identifier '" + id + "'", Status.findStatus(id));
    }
}

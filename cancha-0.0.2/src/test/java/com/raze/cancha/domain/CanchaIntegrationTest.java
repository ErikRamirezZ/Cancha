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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
@RooIntegrationTest(entity = Cancha.class)
public class CanchaIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    CanchaDataOnDemand dod;

	@Test
    public void testCountCanchas() {
        Assert.assertNotNull("Data on demand for 'Cancha' failed to initialize correctly", dod.getRandomCancha());
        long count = Cancha.countCanchas();
        Assert.assertTrue("Counter for 'Cancha' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindCancha() {
        Cancha obj = dod.getRandomCancha();
        Assert.assertNotNull("Data on demand for 'Cancha' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Cancha' failed to provide an identifier", id);
        obj = Cancha.findCancha(id);
        Assert.assertNotNull("Find method for 'Cancha' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Cancha' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllCanchas() {
        Assert.assertNotNull("Data on demand for 'Cancha' failed to initialize correctly", dod.getRandomCancha());
        long count = Cancha.countCanchas();
        Assert.assertTrue("Too expensive to perform a find all test for 'Cancha', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Cancha> result = Cancha.findAllCanchas();
        Assert.assertNotNull("Find all method for 'Cancha' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Cancha' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindCanchaEntries() {
        Assert.assertNotNull("Data on demand for 'Cancha' failed to initialize correctly", dod.getRandomCancha());
        long count = Cancha.countCanchas();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Cancha> result = Cancha.findCanchaEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Cancha' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Cancha' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Cancha obj = dod.getRandomCancha();
        Assert.assertNotNull("Data on demand for 'Cancha' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Cancha' failed to provide an identifier", id);
        obj = Cancha.findCancha(id);
        Assert.assertNotNull("Find method for 'Cancha' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCancha(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Cancha' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Cancha obj = dod.getRandomCancha();
        Assert.assertNotNull("Data on demand for 'Cancha' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Cancha' failed to provide an identifier", id);
        obj = Cancha.findCancha(id);
        boolean modified =  dod.modifyCancha(obj);
        Integer currentVersion = obj.getVersion();
        Cancha merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Cancha' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Cancha' failed to initialize correctly", dod.getRandomCancha());
        Cancha obj = dod.getNewTransientCancha(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Cancha' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Cancha' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Cancha' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Cancha obj = dod.getRandomCancha();
        Assert.assertNotNull("Data on demand for 'Cancha' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Cancha' failed to provide an identifier", id);
        obj = Cancha.findCancha(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Cancha' with identifier '" + id + "'", Cancha.findCancha(id));
    }
}

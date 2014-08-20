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
@RooIntegrationTest(entity = Delegado.class)
public class DelegadoIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    DelegadoDataOnDemand dod;

	@Test
    public void testCountDelegadoes() {
        Assert.assertNotNull("Data on demand for 'Delegado' failed to initialize correctly", dod.getRandomDelegado());
        long count = Delegado.countDelegadoes();
        Assert.assertTrue("Counter for 'Delegado' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindDelegado() {
        Delegado obj = dod.getRandomDelegado();
        Assert.assertNotNull("Data on demand for 'Delegado' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Delegado' failed to provide an identifier", id);
        obj = Delegado.findDelegado(id);
        Assert.assertNotNull("Find method for 'Delegado' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Delegado' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllDelegadoes() {
        Assert.assertNotNull("Data on demand for 'Delegado' failed to initialize correctly", dod.getRandomDelegado());
        long count = Delegado.countDelegadoes();
        Assert.assertTrue("Too expensive to perform a find all test for 'Delegado', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Delegado> result = Delegado.findAllDelegadoes();
        Assert.assertNotNull("Find all method for 'Delegado' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Delegado' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindDelegadoEntries() {
        Assert.assertNotNull("Data on demand for 'Delegado' failed to initialize correctly", dod.getRandomDelegado());
        long count = Delegado.countDelegadoes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Delegado> result = Delegado.findDelegadoEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Delegado' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Delegado' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Delegado obj = dod.getRandomDelegado();
        Assert.assertNotNull("Data on demand for 'Delegado' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Delegado' failed to provide an identifier", id);
        obj = Delegado.findDelegado(id);
        Assert.assertNotNull("Find method for 'Delegado' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyDelegado(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Delegado' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Delegado obj = dod.getRandomDelegado();
        Assert.assertNotNull("Data on demand for 'Delegado' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Delegado' failed to provide an identifier", id);
        obj = Delegado.findDelegado(id);
        boolean modified =  dod.modifyDelegado(obj);
        Integer currentVersion = obj.getVersion();
        Delegado merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Delegado' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Delegado' failed to initialize correctly", dod.getRandomDelegado());
        Delegado obj = dod.getNewTransientDelegado(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Delegado' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Delegado' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Delegado' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Delegado obj = dod.getRandomDelegado();
        Assert.assertNotNull("Data on demand for 'Delegado' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Delegado' failed to provide an identifier", id);
        obj = Delegado.findDelegado(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Delegado' with identifier '" + id + "'", Delegado.findDelegado(id));
    }
}

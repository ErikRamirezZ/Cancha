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

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@RooIntegrationTest(entity = Concepto.class)
public class ConceptoIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ConceptoDataOnDemand dod;

	@Test
    public void testCountConceptoes() {
        Assert.assertNotNull("Data on demand for 'Concepto' failed to initialize correctly", dod.getRandomConcepto());
        long count = Concepto.countConceptoes();
        Assert.assertTrue("Counter for 'Concepto' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindConcepto() {
        Concepto obj = dod.getRandomConcepto();
        Assert.assertNotNull("Data on demand for 'Concepto' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Concepto' failed to provide an identifier", id);
        obj = Concepto.findConcepto(id);
        Assert.assertNotNull("Find method for 'Concepto' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Concepto' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllConceptoes() {
        Assert.assertNotNull("Data on demand for 'Concepto' failed to initialize correctly", dod.getRandomConcepto());
        long count = Concepto.countConceptoes();
        Assert.assertTrue("Too expensive to perform a find all test for 'Concepto', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Concepto> result = Concepto.findAllConceptoes();
        Assert.assertNotNull("Find all method for 'Concepto' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Concepto' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindConceptoEntries() {
        Assert.assertNotNull("Data on demand for 'Concepto' failed to initialize correctly", dod.getRandomConcepto());
        long count = Concepto.countConceptoes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Concepto> result = Concepto.findConceptoEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Concepto' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Concepto' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Concepto obj = dod.getRandomConcepto();
        Assert.assertNotNull("Data on demand for 'Concepto' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Concepto' failed to provide an identifier", id);
        obj = Concepto.findConcepto(id);
        Assert.assertNotNull("Find method for 'Concepto' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyConcepto(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Concepto' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Concepto obj = dod.getRandomConcepto();
        Assert.assertNotNull("Data on demand for 'Concepto' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Concepto' failed to provide an identifier", id);
        obj = Concepto.findConcepto(id);
        boolean modified =  dod.modifyConcepto(obj);
        Integer currentVersion = obj.getVersion();
        Concepto merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Concepto' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Concepto' failed to initialize correctly", dod.getRandomConcepto());
        Concepto obj = dod.getNewTransientConcepto(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Concepto' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Concepto' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Concepto' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Concepto obj = dod.getRandomConcepto();
        Assert.assertNotNull("Data on demand for 'Concepto' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Concepto' failed to provide an identifier", id);
        obj = Concepto.findConcepto(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Concepto' with identifier '" + id + "'", Concepto.findConcepto(id));
    }
}

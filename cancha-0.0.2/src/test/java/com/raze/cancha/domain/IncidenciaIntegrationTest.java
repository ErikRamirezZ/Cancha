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
@RooIntegrationTest(entity = Incidencia.class)
public class IncidenciaIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    IncidenciaDataOnDemand dod;

	@Test
    public void testCountIncidencias() {
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to initialize correctly", dod.getRandomIncidencia());
        long count = Incidencia.countIncidencias();
        Assert.assertTrue("Counter for 'Incidencia' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindIncidencia() {
        Incidencia obj = dod.getRandomIncidencia();
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to provide an identifier", id);
        obj = Incidencia.findIncidencia(id);
        Assert.assertNotNull("Find method for 'Incidencia' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Incidencia' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllIncidencias() {
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to initialize correctly", dod.getRandomIncidencia());
        long count = Incidencia.countIncidencias();
        Assert.assertTrue("Too expensive to perform a find all test for 'Incidencia', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Incidencia> result = Incidencia.findAllIncidencias();
        Assert.assertNotNull("Find all method for 'Incidencia' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Incidencia' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindIncidenciaEntries() {
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to initialize correctly", dod.getRandomIncidencia());
        long count = Incidencia.countIncidencias();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Incidencia> result = Incidencia.findIncidenciaEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Incidencia' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Incidencia' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Incidencia obj = dod.getRandomIncidencia();
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to provide an identifier", id);
        obj = Incidencia.findIncidencia(id);
        Assert.assertNotNull("Find method for 'Incidencia' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyIncidencia(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Incidencia' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Incidencia obj = dod.getRandomIncidencia();
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to provide an identifier", id);
        obj = Incidencia.findIncidencia(id);
        boolean modified =  dod.modifyIncidencia(obj);
        Integer currentVersion = obj.getVersion();
        Incidencia merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Incidencia' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to initialize correctly", dod.getRandomIncidencia());
        Incidencia obj = dod.getNewTransientIncidencia(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Incidencia' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Incidencia' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Incidencia obj = dod.getRandomIncidencia();
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Incidencia' failed to provide an identifier", id);
        obj = Incidencia.findIncidencia(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Incidencia' with identifier '" + id + "'", Incidencia.findIncidencia(id));
    }
}

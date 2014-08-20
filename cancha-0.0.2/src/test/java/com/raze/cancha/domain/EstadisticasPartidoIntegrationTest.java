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
@RooIntegrationTest(entity = EstadisticasPartido.class)
public class EstadisticasPartidoIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    EstadisticasPartidoDataOnDemand dod;

	@Test
    public void testCountEstadisticasPartidoes() {
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to initialize correctly", dod.getRandomEstadisticasPartido());
        long count = EstadisticasPartido.countEstadisticasPartidoes();
        Assert.assertTrue("Counter for 'EstadisticasPartido' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindEstadisticasPartido() {
        EstadisticasPartido obj = dod.getRandomEstadisticasPartido();
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to provide an identifier", id);
        obj = EstadisticasPartido.findEstadisticasPartido(id);
        Assert.assertNotNull("Find method for 'EstadisticasPartido' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'EstadisticasPartido' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllEstadisticasPartidoes() {
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to initialize correctly", dod.getRandomEstadisticasPartido());
        long count = EstadisticasPartido.countEstadisticasPartidoes();
        Assert.assertTrue("Too expensive to perform a find all test for 'EstadisticasPartido', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<EstadisticasPartido> result = EstadisticasPartido.findAllEstadisticasPartidoes();
        Assert.assertNotNull("Find all method for 'EstadisticasPartido' illegally returned null", result);
        Assert.assertTrue("Find all method for 'EstadisticasPartido' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindEstadisticasPartidoEntries() {
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to initialize correctly", dod.getRandomEstadisticasPartido());
        long count = EstadisticasPartido.countEstadisticasPartidoes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<EstadisticasPartido> result = EstadisticasPartido.findEstadisticasPartidoEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'EstadisticasPartido' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'EstadisticasPartido' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        EstadisticasPartido obj = dod.getRandomEstadisticasPartido();
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to provide an identifier", id);
        obj = EstadisticasPartido.findEstadisticasPartido(id);
        Assert.assertNotNull("Find method for 'EstadisticasPartido' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyEstadisticasPartido(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'EstadisticasPartido' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        EstadisticasPartido obj = dod.getRandomEstadisticasPartido();
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to provide an identifier", id);
        obj = EstadisticasPartido.findEstadisticasPartido(id);
        boolean modified =  dod.modifyEstadisticasPartido(obj);
        Integer currentVersion = obj.getVersion();
        EstadisticasPartido merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'EstadisticasPartido' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to initialize correctly", dod.getRandomEstadisticasPartido());
        EstadisticasPartido obj = dod.getNewTransientEstadisticasPartido(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'EstadisticasPartido' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'EstadisticasPartido' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        EstadisticasPartido obj = dod.getRandomEstadisticasPartido();
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'EstadisticasPartido' failed to provide an identifier", id);
        obj = EstadisticasPartido.findEstadisticasPartido(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'EstadisticasPartido' with identifier '" + id + "'", EstadisticasPartido.findEstadisticasPartido(id));
    }
}

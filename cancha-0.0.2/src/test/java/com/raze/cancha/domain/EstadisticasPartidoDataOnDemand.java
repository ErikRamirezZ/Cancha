package com.raze.cancha.domain;
import com.raze.cancha.reference.StatusDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Component
@Configurable
@RooDataOnDemand(entity = EstadisticasPartido.class)
public class EstadisticasPartidoDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<EstadisticasPartido> data;

	@Autowired
    PartidoDataOnDemand partidoDataOnDemand;

	@Autowired
    StatusDataOnDemand statusDataOnDemand;

	public EstadisticasPartido getNewTransientEstadisticasPartido(int index) {
        EstadisticasPartido obj = new EstadisticasPartido();
        setFechaCreacion(obj, index);
        setFechaModificacion(obj, index);
        setMarcadorEquipoLocal(obj, index);
        setMarcadorEquipoVisitante(obj, index);
        setObservaciones(obj, index);
        return obj;
    }

	public void setFechaCreacion(EstadisticasPartido obj, int index) {
        Date fechaCreacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaCreacion(fechaCreacion);
    }

	public void setFechaModificacion(EstadisticasPartido obj, int index) {
        Date fechaModificacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaModificacion(fechaModificacion);
    }

	public void setMarcadorEquipoLocal(EstadisticasPartido obj, int index) {
        int marcadorEquipoLocal = index;
        obj.setMarcadorEquipoLocal(marcadorEquipoLocal);
    }

	public void setMarcadorEquipoVisitante(EstadisticasPartido obj, int index) {
        int marcadorEquipoVisitante = index;
        obj.setMarcadorEquipoVisitante(marcadorEquipoVisitante);
    }

	public void setObservaciones(EstadisticasPartido obj, int index) {
        String observaciones = "observaciones_" + index;
        obj.setObservaciones(observaciones);
    }

	public EstadisticasPartido getSpecificEstadisticasPartido(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        EstadisticasPartido obj = data.get(index);
        Long id = obj.getId();
        return EstadisticasPartido.findEstadisticasPartido(id);
    }

	public EstadisticasPartido getRandomEstadisticasPartido() {
        init();
        EstadisticasPartido obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return EstadisticasPartido.findEstadisticasPartido(id);
    }

	public boolean modifyEstadisticasPartido(EstadisticasPartido obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = EstadisticasPartido.findEstadisticasPartidoEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'EstadisticasPartido' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<EstadisticasPartido>();
        for (int i = 0; i < 10; i++) {
            EstadisticasPartido obj = getNewTransientEstadisticasPartido(i);
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
            data.add(obj);
        }
    }
}

package com.raze.cancha.reference;
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
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Configurable
@Component
@RooDataOnDemand(entity = Modulo.class)
public class ModuloDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Modulo> data;

	public Modulo getNewTransientModulo(int index) {
        Modulo obj = new Modulo();
        setDescripcion(obj, index);
        setFechaCreacion(obj, index);
        setFechaModificacion(obj, index);
        setNombre(obj, index);
        return obj;
    }

	public void setDescripcion(Modulo obj, int index) {
        String descripcion = "descripcion_" + index;
        obj.setDescripcion(descripcion);
    }

	public void setFechaCreacion(Modulo obj, int index) {
        Date fechaCreacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaCreacion(fechaCreacion);
    }

	public void setFechaModificacion(Modulo obj, int index) {
        Date fechaModificacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaModificacion(fechaModificacion);
    }

	public void setNombre(Modulo obj, int index) {
        String nombre = "nombre_" + index;
        obj.setNombre(nombre);
    }

	public Modulo getSpecificModulo(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Modulo obj = data.get(index);
        Long id = obj.getId();
        return Modulo.findModulo(id);
    }

	public Modulo getRandomModulo() {
        init();
        Modulo obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Modulo.findModulo(id);
    }

	public boolean modifyModulo(Modulo obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Modulo.findModuloEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Modulo' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Modulo>();
        for (int i = 0; i < 10; i++) {
            Modulo obj = getNewTransientModulo(i);
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

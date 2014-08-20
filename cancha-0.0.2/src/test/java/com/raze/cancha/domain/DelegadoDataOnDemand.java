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
@RooDataOnDemand(entity = Delegado.class)
public class DelegadoDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Delegado> data;

	@Autowired
    EquipoDataOnDemand equipoDataOnDemand;

	@Autowired
    StatusDataOnDemand statusDataOnDemand;

	public Delegado getNewTransientDelegado(int index) {
        Delegado obj = new Delegado();
        setApellidoMaterno(obj, index);
        setApellidoPaterno(obj, index);
        setCelular(obj, index);
        setCorreoE(obj, index);
        setDomicilio(obj, index);
        setFechaCreacion(obj, index);
        setFechaModificacion(obj, index);
        setNombre(obj, index);
        setTelefono(obj, index);
        return obj;
    }

	public void setApellidoMaterno(Delegado obj, int index) {
        String apellidoMaterno = "apellidoMaterno_" + index;
        obj.setApellidoMaterno(apellidoMaterno);
    }

	public void setApellidoPaterno(Delegado obj, int index) {
        String apellidoPaterno = "apellidoPaterno_" + index;
        obj.setApellidoPaterno(apellidoPaterno);
    }

	public void setCelular(Delegado obj, int index) {
        String celular = "celular_" + index;
        obj.setCelular(celular);
    }

	public void setCorreoE(Delegado obj, int index) {
        String correoE = "correoE_" + index;
        obj.setCorreoE(correoE);
    }

	public void setDomicilio(Delegado obj, int index) {
        String domicilio = "domicilio_" + index;
        obj.setDomicilio(domicilio);
    }

	public void setFechaCreacion(Delegado obj, int index) {
        Date fechaCreacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaCreacion(fechaCreacion);
    }

	public void setFechaModificacion(Delegado obj, int index) {
        Date fechaModificacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaModificacion(fechaModificacion);
    }

	public void setNombre(Delegado obj, int index) {
        String nombre = "nombre_" + index;
        obj.setNombre(nombre);
    }

	public void setTelefono(Delegado obj, int index) {
        String telefono = "telefono_" + index;
        obj.setTelefono(telefono);
    }

	public Delegado getSpecificDelegado(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Delegado obj = data.get(index);
        Long id = obj.getId();
        return Delegado.findDelegado(id);
    }

	public Delegado getRandomDelegado() {
        init();
        Delegado obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Delegado.findDelegado(id);
    }

	public boolean modifyDelegado(Delegado obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Delegado.findDelegadoEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Delegado' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Delegado>();
        for (int i = 0; i < 10; i++) {
            Delegado obj = getNewTransientDelegado(i);
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

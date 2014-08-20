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

@Configurable
@Component
@RooDataOnDemand(entity = Arbitro.class)
public class ArbitroDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Arbitro> data;

	@Autowired
    StatusDataOnDemand statusDataOnDemand;

	public Arbitro getNewTransientArbitro(int index) {
        Arbitro obj = new Arbitro();
        setApellidoMaterno(obj, index);
        setApellidoPaterno(obj, index);
        setCelular(obj, index);
        setCorreoE(obj, index);
        setDomicilio(obj, index);
        setEdad(obj, index);
        setFechaCreacion(obj, index);
        setFechaModificacion(obj, index);
        setNombre(obj, index);
        setTelefono(obj, index);
        return obj;
    }

	public void setApellidoMaterno(Arbitro obj, int index) {
        String apellidoMaterno = "apellidoMaterno_" + index;
        obj.setApellidoMaterno(apellidoMaterno);
    }

	public void setApellidoPaterno(Arbitro obj, int index) {
        String apellidoPaterno = "apellidoPaterno_" + index;
        obj.setApellidoPaterno(apellidoPaterno);
    }

	public void setCelular(Arbitro obj, int index) {
        String celular = "celular_" + index;
        obj.setCelular(celular);
    }

	public void setCorreoE(Arbitro obj, int index) {
        String correoE = "correoE_" + index;
        obj.setCorreoE(correoE);
    }

	public void setDomicilio(Arbitro obj, int index) {
        String domicilio = "domicilio_" + index;
        obj.setDomicilio(domicilio);
    }

	public void setEdad(Arbitro obj, int index) {
        int edad = index;
        obj.setEdad(edad);
    }

	public void setFechaCreacion(Arbitro obj, int index) {
        Date fechaCreacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaCreacion(fechaCreacion);
    }

	public void setFechaModificacion(Arbitro obj, int index) {
        Date fechaModificacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaModificacion(fechaModificacion);
    }

	public void setNombre(Arbitro obj, int index) {
        String nombre = "nombre_" + index;
        obj.setNombre(nombre);
    }

	public void setTelefono(Arbitro obj, int index) {
        String telefono = "telefono_" + index;
        obj.setTelefono(telefono);
    }

	public Arbitro getSpecificArbitro(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Arbitro obj = data.get(index);
        Long id = obj.getId();
        return Arbitro.findArbitro(id);
    }

	public Arbitro getRandomArbitro() {
        init();
        Arbitro obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Arbitro.findArbitro(id);
    }

	public boolean modifyArbitro(Arbitro obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Arbitro.findArbitroEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Arbitro' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Arbitro>();
        for (int i = 0; i < 10; i++) {
            Arbitro obj = getNewTransientArbitro(i);
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

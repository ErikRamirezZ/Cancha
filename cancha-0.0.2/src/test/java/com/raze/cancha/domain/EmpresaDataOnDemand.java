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
@RooDataOnDemand(entity = Empresa.class)
public class EmpresaDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Empresa> data;

	@Autowired
    StatusDataOnDemand statusDataOnDemand;

	public Empresa getNewTransientEmpresa(int index) {
        Empresa obj = new Empresa();
        setDomicilio(obj, index);
        setFechaCreacion(obj, index);
        setFechaModificacion(obj, index);
        setLogo(obj, index);
        setNombre(obj, index);
        setNombreComercial(obj, index);
        setTelefono(obj, index);
        return obj;
    }

	public void setDomicilio(Empresa obj, int index) {
        String domicilio = "domicilio_" + index;
        obj.setDomicilio(domicilio);
    }

	public void setFechaCreacion(Empresa obj, int index) {
        Date fechaCreacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaCreacion(fechaCreacion);
    }

	public void setFechaModificacion(Empresa obj, int index) {
        Date fechaModificacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaModificacion(fechaModificacion);
    }

	public void setLogo(Empresa obj, int index) {
        byte[] logo = String.valueOf(index).getBytes();
        obj.setLogo(logo);
    }

	public void setNombre(Empresa obj, int index) {
        String nombre = "nombre_" + index;
        obj.setNombre(nombre);
    }

	public void setNombreComercial(Empresa obj, int index) {
        String nombreComercial = "nombreComercial_" + index;
        obj.setNombreComercial(nombreComercial);
    }

	public void setTelefono(Empresa obj, int index) {
        String telefono = "telefono_" + index;
        obj.setTelefono(telefono);
    }

	public Empresa getSpecificEmpresa(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Empresa obj = data.get(index);
        Long id = obj.getId();
        return Empresa.findEmpresa(id);
    }

	public Empresa getRandomEmpresa() {
        init();
        Empresa obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Empresa.findEmpresa(id);
    }

	public boolean modifyEmpresa(Empresa obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Empresa.findEmpresaEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Empresa' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Empresa>();
        for (int i = 0; i < 10; i++) {
            Empresa obj = getNewTransientEmpresa(i);
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

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
@RooDataOnDemand(entity = Usuario.class)
public class UsuarioDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Usuario> data;

	@Autowired
    EmpresaDataOnDemand empresaDataOnDemand;

	@Autowired
    StatusDataOnDemand statusDataOnDemand;

	public Usuario getNewTransientUsuario(int index) {
        Usuario obj = new Usuario();
        setContrasena(obj, index);
        setFechaCreacion(obj, index);
        setFechaModificacion(obj, index);
        setIntentos(obj, index);
        setUltimaFechaAcceso(obj, index);
        setUsuario(obj, index);
        return obj;
    }

	public void setContrasena(Usuario obj, int index) {
        String contrasena = "contrasena_" + index;
        obj.setContrasena(contrasena);
    }

	public void setFechaCreacion(Usuario obj, int index) {
        Date fechaCreacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaCreacion(fechaCreacion);
    }

	public void setFechaModificacion(Usuario obj, int index) {
        Date fechaModificacion = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setFechaModificacion(fechaModificacion);
    }

	public void setIntentos(Usuario obj, int index) {
        int intentos = index;
        obj.setIntentos(intentos);
    }

	public void setUltimaFechaAcceso(Usuario obj, int index) {
        Date ultimaFechaAcceso = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUltimaFechaAcceso(ultimaFechaAcceso);
    }

	public void setUsuario(Usuario obj, int index) {
        String usuario = "usuario_" + index;
        obj.setUsuario(usuario);
    }

	public Usuario getSpecificUsuario(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Usuario obj = data.get(index);
        Long id = obj.getId();
        return Usuario.findUsuario(id);
    }

	public Usuario getRandomUsuario() {
        init();
        Usuario obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Usuario.findUsuario(id);
    }

	public boolean modifyUsuario(Usuario obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Usuario.findUsuarioEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Usuario' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Usuario>();
        for (int i = 0; i < 10; i++) {
            Usuario obj = getNewTransientUsuario(i);
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

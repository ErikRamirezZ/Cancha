package com.raze.cancha.domain;
import java.security.SecureRandom;
import java.util.ArrayList;
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
@RooDataOnDemand(entity = Alineacion.class)
public class AlineacionDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Alineacion> data;

	@Autowired
    JugadorDataOnDemand jugadorDataOnDemand;

	@Autowired
    PartidoDataOnDemand partidoDataOnDemand;

	public Alineacion getNewTransientAlineacion(int index) {
        Alineacion obj = new Alineacion();
        return obj;
    }

	public Alineacion getSpecificAlineacion(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Alineacion obj = data.get(index);
        Long id = obj.getId();
        return Alineacion.findAlineacion(id);
    }

	public Alineacion getRandomAlineacion() {
        init();
        Alineacion obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Alineacion.findAlineacion(id);
    }

	public boolean modifyAlineacion(Alineacion obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Alineacion.findAlineacionEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Alineacion' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Alineacion>();
        for (int i = 0; i < 10; i++) {
            Alineacion obj = getNewTransientAlineacion(i);
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

package com.raze.cancha.domain;
import com.raze.cancha.reference.StatusDataOnDemand;
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
@RooDataOnDemand(entity = EquipoTorneo.class)
public class EquipoTorneoDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<EquipoTorneo> data;

	@Autowired
    EquipoDataOnDemand equipoDataOnDemand;

	@Autowired
    TorneoDataOnDemand torneoDataOnDemand;

	@Autowired
    StatusDataOnDemand statusDataOnDemand;

	public EquipoTorneo getNewTransientEquipoTorneo(int index) {
        EquipoTorneo obj = new EquipoTorneo();
        return obj;
    }

	public EquipoTorneo getSpecificEquipoTorneo(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        EquipoTorneo obj = data.get(index);
        Long id = obj.getId();
        return EquipoTorneo.findEquipoTorneo(id);
    }

	public EquipoTorneo getRandomEquipoTorneo() {
        init();
        EquipoTorneo obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return EquipoTorneo.findEquipoTorneo(id);
    }

	public boolean modifyEquipoTorneo(EquipoTorneo obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = EquipoTorneo.findEquipoTorneoEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'EquipoTorneo' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<EquipoTorneo>();
        for (int i = 0; i < 10; i++) {
            EquipoTorneo obj = getNewTransientEquipoTorneo(i);
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

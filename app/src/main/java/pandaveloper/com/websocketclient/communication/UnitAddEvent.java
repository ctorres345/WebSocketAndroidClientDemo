package pandaveloper.com.websocketclient.communication;

import pandaveloper.com.websocketclient.model.bean.UnitBean;

/**
 * Created by cesar on 2/23/17.
 */

public class UnitAddEvent {
    private final UnitBean unit;

    public UnitAddEvent(UnitBean unit) {
        this.unit = unit;
    }

    public UnitBean getUnit() {
        return unit;
    }
}

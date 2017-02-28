package pandaveloper.com.websocketclient.communication;

/**
 * Created by cesar on 2/23/17.
 */

public class UnitDeleteEvent {
    private final long unitId;

    public UnitDeleteEvent(long unitId) {
        this.unitId = unitId;
    }

    public long getUnitId() {
        return unitId;
    }

}

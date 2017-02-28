package pandaveloper.com.websocketclient.model.dto;

/**
 * Created by cesar on 1/31/17.
 */

public class BaseWebSocketDTO {
    private long actionCode;

    public BaseWebSocketDTO(long actionCode) {
        this.actionCode = actionCode;
    }

    public long getActionCode() {
        return actionCode;
    }

    public void setActionCode(long actionCode) {
        this.actionCode = actionCode;
    }
}

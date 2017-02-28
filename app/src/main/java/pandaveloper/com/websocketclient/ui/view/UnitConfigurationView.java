package pandaveloper.com.websocketclient.ui.view;

import pandaveloper.com.websocketclient.ui.base.BaseView;

/**
 * Created by cesar on 2/16/17.
 */

public interface UnitConfigurationView extends BaseView {
    void connectWebSocket();
    void showNoResponse();
    void showResponse();
    void updateCurrentUnitNumber(int size);
}

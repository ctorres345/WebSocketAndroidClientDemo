package pandaveloper.com.websocketclient.presenter.iface;

import pandaveloper.com.websocketclient.model.dto.UnitDTO;

/**
 * Created by cesar on 2/14/17.
 */

public interface UnitConfigurationPresenter {
    void loadTestUnitList();

    void openWebSocket();

    void closeWebSocket();

    void registerUnit(UnitDTO unitDTO);

    void deleteUnit(UnitDTO unitDTO);

    void loadData();
}

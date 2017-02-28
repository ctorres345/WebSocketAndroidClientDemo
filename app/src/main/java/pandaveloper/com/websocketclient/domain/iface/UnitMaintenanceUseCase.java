package pandaveloper.com.websocketclient.domain.iface;

import java.util.List;

import pandaveloper.com.websocketclient.model.bean.UnitBean;
import pandaveloper.com.websocketclient.model.dto.UnitDTO;

/**
 * Created by cesar on 2/23/17.
 */

public interface UnitMaintenanceUseCase {
    void openWebSocket(OnUnitReceivedListener onUnitReceivedListener,OnUnitsAddedListener onUnitsAddedListener, OnUnitDeletedListener onUnitDeletedListener);

    void closeWebSocket();

    interface OnUnitDeletedListener{
        void onUnitDeleted(long unitId);
    }

    void deleteUnit(UnitDTO unitDTO);

    interface OnUnitReceivedListener{
        void onCurrentUnitsReceived(List<UnitBean> unitDTOList);
    };

    void getCurrentUnits(OnUnitReceivedListener listener);

    interface OnUnitsAddedListener{
        void onUnitAdded(UnitBean unit);
    }

    void registerUnit(UnitDTO unitDTO);

    void registerTestUnit(OnUnitsAddedListener onUnitsAddedListener,UnitDTO unitDTO);
}

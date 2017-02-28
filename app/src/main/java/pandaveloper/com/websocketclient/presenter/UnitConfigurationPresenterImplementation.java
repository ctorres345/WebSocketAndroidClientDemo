package pandaveloper.com.websocketclient.presenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import pandaveloper.com.websocketclient.BuildConfig;
import pandaveloper.com.websocketclient.communication.UnitAddEvent;
import pandaveloper.com.websocketclient.communication.UnitDeleteEvent;
import pandaveloper.com.websocketclient.communication.UnitListEvent;
import pandaveloper.com.websocketclient.domain.UnitMaintenanceUseCaseController;
import pandaveloper.com.websocketclient.domain.iface.UnitMaintenanceUseCase;
import pandaveloper.com.websocketclient.model.bean.UnitBean;
import pandaveloper.com.websocketclient.model.dto.UnitDTO;
import pandaveloper.com.websocketclient.presenter.iface.UnitConfigurationPresenter;
import pandaveloper.com.websocketclient.ui.view.UnitConfigurationView;

/**
 * Created by cesar on 2/14/17.
 */

public class UnitConfigurationPresenterImplementation implements UnitConfigurationPresenter,UnitMaintenanceUseCase.OnUnitDeletedListener,UnitMaintenanceUseCase.OnUnitsAddedListener,UnitMaintenanceUseCase.OnUnitReceivedListener{
    private UnitConfigurationView unitConfigurationView;
    private UnitMaintenanceUseCase unitMaintenanceUseCase;

    public UnitConfigurationPresenterImplementation(UnitConfigurationView unitConfigurationView) {
        this.unitConfigurationView = unitConfigurationView;
        this.unitMaintenanceUseCase = new UnitMaintenanceUseCaseController();
    }

    @Override
    public void loadTestUnitList() {
        if(unitConfigurationView != null){
            unitConfigurationView.showLoading();
        }
        unitMaintenanceUseCase.getCurrentUnits(this);
    }

    @Override
    public void openWebSocket() {
        if(unitConfigurationView != null){
            unitConfigurationView.showLoading();
        }
        unitMaintenanceUseCase.openWebSocket(this,this,this);
    }

    @Override
    public void closeWebSocket() {
        if(unitConfigurationView != null){
            unitConfigurationView.showLoading();
        }
        unitMaintenanceUseCase.closeWebSocket();
    }

    @Override
    public void registerUnit(UnitDTO unitDTO) {
        if(BuildConfig.IS_FOR_TESTING){
            unitMaintenanceUseCase.registerTestUnit(this,unitDTO);
        }else{
            unitMaintenanceUseCase.registerUnit(unitDTO);
        }
    }

    @Override
    public void deleteUnit(UnitDTO unitDTO) {
        unitMaintenanceUseCase.deleteUnit(unitDTO);
    }

    @Override
    public void loadData() {
        if(unitConfigurationView != null){
            if(BuildConfig.IS_FOR_TESTING){
                loadTestUnitList();
            }else{
                unitConfigurationView.connectWebSocket();
            }
        }
    }

    @Override
    public void onCurrentUnitsReceived(List<UnitBean> unitBeanList) {
        if(unitConfigurationView != null){
            unitConfigurationView.dismissLoading();
        }
        EventBus.getDefault().post(new UnitListEvent(unitBeanList));
    }

    @Override
    public void onUnitDeleted(long unitId) {
        EventBus.getDefault().post(new UnitDeleteEvent(unitId));
    }

    @Override
    public void onUnitAdded(UnitBean unit) {
        EventBus.getDefault().post(new UnitAddEvent(unit));
    }
}

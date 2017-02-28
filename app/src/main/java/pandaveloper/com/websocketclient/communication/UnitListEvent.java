package pandaveloper.com.websocketclient.communication;

import java.util.List;

import pandaveloper.com.websocketclient.model.bean.UnitBean;

/**
 * Created by cesar on 2/16/17.
 */

public class UnitListEvent {
    private final List<UnitBean> unitBeanList;

    public UnitListEvent(List<UnitBean> unitBeanList) {
        this.unitBeanList = unitBeanList;
    }

    public List<UnitBean> getUnitBeanList() {
        return unitBeanList;
    }
}

package pandaveloper.com.websocketclient.model.dto;

import pandaveloper.com.websocketclient.model.bean.UnitBean;
import pandaveloper.com.websocketclient.model.enums.UNIT_CLASS_TYPE;

/**
 * Created by cesar on 1/26/17.
 */

public class UnitDTO extends BaseWebSocketDTO {
    private long id;
    private String name;
    private long classType;

    public void setClassType(long classType) {
        this.classType = classType;
    }

    public UnitDTO(long action) {
        super(action);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getClassType() {
        return classType;
    }

    public UnitBean toBean(){
        UnitBean unitBean = new UnitBean();
        unitBean.setId(this.id);
        unitBean.setName(this.name);
        unitBean.setClassType((this.getClassType() == UNIT_CLASS_TYPE.ARCHER.getCode()) ? UNIT_CLASS_TYPE.ARCHER :
                (this.getClassType() == UNIT_CLASS_TYPE.KNIGHT.getCode()) ? UNIT_CLASS_TYPE.KNIGHT :
                        (this.getClassType() == UNIT_CLASS_TYPE.LANCER.getCode()) ? UNIT_CLASS_TYPE.LANCER : null);
        return unitBean;
    }
}

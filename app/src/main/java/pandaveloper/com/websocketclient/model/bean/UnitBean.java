package pandaveloper.com.websocketclient.model.bean;

import pandaveloper.com.websocketclient.model.dto.UnitDTO;
import pandaveloper.com.websocketclient.model.enums.UNIT_CLASS_TYPE;

/**
 * Created by cesar on 2/16/17.
 */

public class UnitBean {
    private long id;
    private String name;
    private UNIT_CLASS_TYPE classType;

    public UnitBean(long id, String name, UNIT_CLASS_TYPE classType) {
        this.id = id;
        this.name = name;
        this.classType = classType;
    }

    public UnitBean() {
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

    public UNIT_CLASS_TYPE getClassType() {
        return classType;
    }

    public void setClassType(UNIT_CLASS_TYPE classType) {
        this.classType = classType;
    }

    public static UnitBean fromDTO(UnitDTO unitDTO){
        return unitDTO.toBean();
    }
}

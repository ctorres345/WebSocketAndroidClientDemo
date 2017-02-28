package pandaveloper.com.websocketclient.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import pandaveloper.com.websocketclient.R;
import pandaveloper.com.websocketclient.model.dto.UnitDTO;
import pandaveloper.com.websocketclient.model.enums.UNIT_CLASS_TYPE;
import pandaveloper.com.websocketclient.model.enums.WEB_SOCKET_ACTION;

/**
 * Created by cesar on 1/26/17.
 */

public class UnitCreationDialog extends Dialog {
    @BindView(R.id.etUnitName)protected EditText etUnitName;
    @BindView(R.id.btnRegister)protected Button btnRegister;
    @BindView(R.id.spUnitClass)protected Spinner spUnitClass;
    private UnitCreationDialogInterface dialogInterface;

    public UnitCreationDialog(Context context, UnitCreationDialogInterface dialogInterface) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_unit_registration);
        setDialogInterface(dialogInterface);
        ButterKnife.bind(this);
        initUI();
    }



    private void initUI() {
        spUnitClass.setAdapter(new ArrayAdapter<UNIT_CLASS_TYPE>(getContext(),android.R.layout.simple_list_item_1,UNIT_CLASS_TYPE.values()));
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields()){
                    UnitDTO unitDTO = new UnitDTO(WEB_SOCKET_ACTION.ADD.getCode());
                    unitDTO.setName(etUnitName.getText().toString().trim());
                    unitDTO.setClassType(((UNIT_CLASS_TYPE)spUnitClass.getSelectedItem()).getCode());
                    getDialogInterface().registerUnit(unitDTO);
                    dismiss();
                }
            }
        });
    }

    private boolean validateFields() {
        if(etUnitName.getText().toString().isEmpty()){
            etUnitName.setError("No puede estar vacio");
            return false;
        }
        return true;
    }

    public interface UnitCreationDialogInterface{
        void registerUnit(UnitDTO unitDTO);
    }

    public UnitCreationDialogInterface getDialogInterface() {
        return dialogInterface;
    }

    public void setDialogInterface(UnitCreationDialogInterface dialogInterface) {
        this.dialogInterface = dialogInterface;
    }
}

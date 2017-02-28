package pandaveloper.com.websocketclient.domain;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pandaveloper.com.websocketclient.commons.Constants;
import pandaveloper.com.websocketclient.domain.iface.UnitMaintenanceUseCase;
import pandaveloper.com.websocketclient.model.bean.UnitBean;
import pandaveloper.com.websocketclient.model.dto.UnitDTO;
import pandaveloper.com.websocketclient.model.enums.UNIT_CLASS_TYPE;
import pandaveloper.com.websocketclient.model.enums.WEB_SOCKET_ACTION;

/**
 * Created by cesar on 2/23/17.
 */

public class UnitMaintenanceUseCaseController implements UnitMaintenanceUseCase {
    private WebSocketClient webSocketClient;

    @Override
    public void openWebSocket(final OnUnitReceivedListener onUnitReceivedListener, final OnUnitsAddedListener onUnitsAddedListener, final OnUnitDeletedListener onUnitDeletedListener) {
        URI uri;
        try {
            uri = new URI(Constants.ARMY_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        Log.v("URL: ",Constants.ARMY_URL);
        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
            }

            @Override
            public void onMessage(final String s) {
                try{
                    final JSONObject jsonObject = new JSONObject(s);
                    long actionCode = Long.valueOf(jsonObject.getString("actionCode"));
                    final String dataObject = jsonObject.getString("data");
                    Gson gson = new GsonBuilder().create();
                    if(actionCode == WEB_SOCKET_ACTION.GET.getCode()){
                        List<UnitDTO> unitDTOList = new ArrayList<>();
                        List<UnitBean> unitBeanList = new ArrayList<>();
                        Type sequenceListType = new TypeToken<List<UnitDTO>>() {
                        }.getType();
                        unitDTOList = gson.fromJson(dataObject, sequenceListType);
                        for(UnitDTO unitDTO : unitDTOList){
                            unitBeanList.add(UnitBean.fromDTO(unitDTO));
                        }
                        onUnitReceivedListener.onCurrentUnitsReceived(unitBeanList);
                    }else if(actionCode == WEB_SOCKET_ACTION.ADD.getCode()){
                        final UnitDTO unitDTO = gson.fromJson(dataObject,UnitDTO.class);
                        if(unitDTO != null){
                            onUnitsAddedListener.onUnitAdded(UnitBean.fromDTO(unitDTO));
                        }
                    }else if(actionCode == WEB_SOCKET_ACTION.REMOVE.getCode()){
                        JSONObject data = new JSONObject(dataObject);
                        if(data != null){
                            long unitId = Long.valueOf(data.getString("id"));
                            onUnitDeletedListener.onUnitDeleted(unitId);
                        }
                    }else{
                        return;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    return;
                }
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        webSocketClient.connect();
    }

    @Override
    public void closeWebSocket() {
        if(getWebSocketClient() != null){
            getWebSocketClient().close();
        }
    }

    @Override
    public void deleteUnit(UnitDTO unitDTO) {
        if(getWebSocketClient() != null && unitDTO != null){
            Gson gson = new GsonBuilder().serializeNulls().create();
            String json = gson.toJson(unitDTO);
            Log.v("Websocket_Delete","params : " + json);
            getWebSocketClient().send(json);
        }
    }

    @Override
    public void getCurrentUnits(final OnUnitReceivedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onCurrentUnitsReceived(getTestUnits());
            }
        },1000);
    }

    @Override
    public void registerUnit(UnitDTO unitDTO) {
        if(getWebSocketClient() != null && unitDTO != null){
            Gson gson = new GsonBuilder().serializeNulls().create();
            String json = gson.toJson(unitDTO);
            Log.v("Websocket_Register","params : " + json);
            getWebSocketClient().send(json);
        }
    }

    @Override
    public void registerTestUnit(OnUnitsAddedListener onUnitsAddedListener,UnitDTO unitDTO) {
        if(onUnitsAddedListener != null && unitDTO != null){
            onUnitsAddedListener.onUnitAdded(UnitBean.fromDTO(unitDTO));
        }
    }

    public WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    private List<UnitBean> getTestUnits() {
        List<UnitBean> testUnitBeanList = new ArrayList<>();
        testUnitBeanList.add(new UnitBean(testUnitBeanList.size(),"Frederick", UNIT_CLASS_TYPE.KNIGHT));
        testUnitBeanList.add(new UnitBean(testUnitBeanList.size(),"Takumi", UNIT_CLASS_TYPE.ARCHER));
        testUnitBeanList.add(new UnitBean(testUnitBeanList.size(),"Mozu", UNIT_CLASS_TYPE.LANCER));
        testUnitBeanList.add(new UnitBean(testUnitBeanList.size(),"Chrom", UNIT_CLASS_TYPE.LORD));
        testUnitBeanList.add(new UnitBean(testUnitBeanList.size(),"Donnel", UNIT_CLASS_TYPE.LANCER));
        return testUnitBeanList;
    }
}

package pandaveloper.com.websocketclient.ui.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import butterknife.BindView;
import pandaveloper.com.websocketclient.R;
import pandaveloper.com.websocketclient.communication.UnitAddEvent;
import pandaveloper.com.websocketclient.communication.UnitDeleteEvent;
import pandaveloper.com.websocketclient.communication.UnitListEvent;
import pandaveloper.com.websocketclient.model.bean.UnitBean;
import pandaveloper.com.websocketclient.model.dto.UnitDTO;
import pandaveloper.com.websocketclient.presenter.UnitConfigurationPresenterImplementation;
import pandaveloper.com.websocketclient.presenter.iface.UnitConfigurationPresenter;
import pandaveloper.com.websocketclient.ui.adapters.UnitAdapter;
import pandaveloper.com.websocketclient.ui.base.BaseFragment;
import pandaveloper.com.websocketclient.ui.dialog.UnitCreationDialog;
import pandaveloper.com.websocketclient.ui.view.UnitConfigurationView;

/**
 * Created by cesar on 2/16/17.
 */

public class UnitConfigurationFragment extends BaseFragment implements UnitCreationDialog.UnitCreationDialogInterface,UnitConfigurationView {

    @BindView(R.id.rvUnits)protected RecyclerView rvUnits;
    @BindView(R.id.tvCurrentUnits)protected TextView tvCurrentUnits;
    @BindView(R.id.tvNoResponse)protected TextView tvNoResponse;
    @BindView(R.id.fabAddUnit)protected FloatingActionButton fab;
    private UnitConfigurationPresenter presenter;
    private UnitAdapter unitAdapter;

    public static UnitConfigurationFragment newInstance() {
        return new UnitConfigurationFragment();
    }

    @Override
    protected void initUI() {
        presenter = new UnitConfigurationPresenterImplementation(this);
        unitAdapter = new UnitAdapter(new ArrayList<UnitBean>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getCurrentActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvUnits.getContext(),
                layoutManager.getOrientation());
        rvUnits.setLayoutManager(layoutManager);
        rvUnits.addItemDecoration(dividerItemDecoration);
        rvUnits.setAdapter(unitAdapter);
        rvUnits.addOnScrollListener(onUnitListScrolled);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnitCreationDialog dialog = new UnitCreationDialog(getActivity(),UnitConfigurationFragment.this);
                dialog.show();
            }
        });
        presenter.loadData();
    }

    @Override
    public void showLoading() {
        showLoadingLayout();
    }

    @Override
    public void dismissLoading() {
        dismissLoadingLayout();
    }

    @Override
    protected int getContextLayout() {
        return R.layout.unit_configuration_fragment;
    }

    @Override
    public void registerUnit(UnitDTO unitDTO) {
        presenter.registerUnit(unitDTO);
    }

    @Override
    public void connectWebSocket() {
        presenter.openWebSocket();
    }

    @Override
    public void showNoResponse() {
        if(getView() != null && tvNoResponse != null && rvUnits != null){
            rvUnits.setVisibility(View.GONE);
            tvNoResponse.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showResponse() {
        if(getView() != null && tvNoResponse != null && rvUnits != null){
            rvUnits.setVisibility(View.VISIBLE);
            tvNoResponse.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateCurrentUnitNumber(int size) {
        if(size > 0){
            tvCurrentUnits.setText("Current Units (" + size + ")");
        }else{
            tvCurrentUnits.setText("Current Units");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnitGetListReceivedEvent(UnitListEvent event){
        if(event.getUnitBeanList() != null && !event.getUnitBeanList().isEmpty()){
            showResponse();
            updateCurrentUnitNumber(event.getUnitBeanList().size());
            unitAdapter.setUnitBeanList(event.getUnitBeanList());
            unitAdapter.notifyDataSetChanged();
        }else{
            updateCurrentUnitNumber(0);
            showNoResponse();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnitAddEvent(UnitAddEvent event){
        if(event.getUnit() != null && unitAdapter != null){
            if(unitAdapter.getUnitBeanList().isEmpty()){
                showResponse();
            }
            UnitBean unit = event.getUnit();
            if(unit.getId() == 0){
                unit.setId(unitAdapter.getItemCount());
            }
            unitAdapter.getUnitBeanList().add(unit);
            unitAdapter.notifyItemInserted(unitAdapter.getUnitBeanList().size());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnitDeleteEvent(UnitDeleteEvent event){
        if(event.getUnitId() > 0){
            Iterator<UnitBean> iterator = unitAdapter.getUnitBeanList().iterator();
            while (iterator.hasNext()){
                UnitBean unitBean = iterator.next();
                if(unitBean.getId() == event.getUnitId()){
                    iterator.remove();
                    break;
                }
            }
            unitAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        presenter.closeWebSocket();
    }

    RecyclerView.OnScrollListener onUnitListScrolled = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if(fab != null){
                if (dy > 0){
                    fab.hide();
                }else{
                    fab.show();
                }
            }
        }
    };
}

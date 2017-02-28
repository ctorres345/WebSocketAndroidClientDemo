package pandaveloper.com.websocketclient.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pandaveloper.com.websocketclient.MainActivity;

/**
 * Created by cesar on 2/16/17.
 */

public abstract class BaseFragment extends Fragment {
    private MainActivity mainActivity;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContextLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        initUI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract void initUI();

    protected abstract int getContextLayout();

    public void showLoadingLayout(){
        if(getCurrentActivity() != null && getCurrentActivity().getPbLoading() != null){
            getCurrentActivity().getPbLoading().setVisibility(View.VISIBLE);
        }
    }

    public void dismissLoadingLayout(){
        if(getCurrentActivity() != null && getCurrentActivity().getPbLoading() != null){
            if(getCurrentActivity().getPbLoading().getVisibility() == View.VISIBLE){
                getCurrentActivity().getPbLoading().setVisibility(View.GONE);
            }
        }
    }


    public MainActivity getCurrentActivity() {
        return mainActivity;
    }
}

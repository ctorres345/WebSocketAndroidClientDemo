package pandaveloper.com.websocketclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import pandaveloper.com.websocketclient.ui.fragment.UnitConfigurationFragment;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.pbLoading)protected ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        UnitConfigurationFragment fragment = UnitConfigurationFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,fragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public ProgressBar getPbLoading() {
        return pbLoading;
    }

    public void setPbLoading(ProgressBar pbLoading) {
        this.pbLoading = pbLoading;
    }
}

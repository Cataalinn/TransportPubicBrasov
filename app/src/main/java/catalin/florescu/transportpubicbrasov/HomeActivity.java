package catalin.florescu.transportpubicbrasov;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import catalin.florescu.transportpubicbrasov.Helpers.CreateFragment;
import catalin.florescu.transportpubicbrasov.Helpers.DrawerToggleAnimatorHelper;
import catalin.florescu.transportpubicbrasov.Views.ClockFragment;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                0,
                0);

        drawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DrawerToggleAnimatorHelper.isArrow()) {
                    onBackPressed();
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        CreateFragment.createFragmentSupport(this, new ClockFragment(), R.id.content);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ButterKnife.bind(this);
    }

    public ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

}

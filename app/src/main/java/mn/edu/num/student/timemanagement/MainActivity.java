package mn.edu.num.student.timemanagement;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(0xFFFFFFFF);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final Button bLogout = (Button) findViewById(R.id.bLogout);
        final Button bSchedule = (Button) findViewById(R.id.bSchedule);

        if (((GlobalApplication) this.getApplication()).uid == -1){
            bLogin.setVisibility(View.VISIBLE);
            bRegister.setVisibility(View.VISIBLE);
            bLogout.setVisibility(View.GONE);
            bSchedule.setVisibility(View.GONE);
        }
        else {
            bLogin.setVisibility(View.GONE);
            bRegister.setVisibility(View.GONE);
            bLogout.setVisibility(View.VISIBLE);
            bSchedule.setVisibility(View.VISIBLE);
        }

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(loginIntent);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        bLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                set();
                Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(mainIntent);
            }
        });

        bSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scheduleIntent = new Intent(MainActivity.this, ShowScheduleActivity.class);
                MainActivity.this.startActivity(scheduleIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void set(){
        ((GlobalApplication) this.getApplication()).set(-1,null,null,null,null,0);
        return;
    }
}

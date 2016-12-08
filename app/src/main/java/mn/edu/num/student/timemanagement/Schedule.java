package mn.edu.num.student.timemanagement;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.choose_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        try{
            ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

                String[] items = getResources().getStringArray(R.array.choose_array); // List items from res

                @Override
                public boolean onNavigationItemSelected(int position, long id) {

                    Log.d("NavigationItemSelected", items[position]);

                    if(items[position].equalsIgnoreCase("Day")){
                        DayFragment dayFragment = new DayFragment();

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dayFragment).commit();
                    }
                    else if(items[position].equals("Week")){
                        WeekFragment weekFragment = new WeekFragment();

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, weekFragment).commit();
                    }
                    else if(items[position].equals("Month")){
                        MonthFragment monthFragment = new MonthFragment();

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, monthFragment).commit();
                    }

                    return true;
                }

            };

            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.getSupportActionBar().setNavigationMode(this.getSupportActionBar().NAVIGATION_MODE_LIST);
            this.getSupportActionBar().setDisplayShowTitleEnabled(false);
            this.getSupportActionBar().setListNavigationCallbacks(adapter, callback);
        }
        catch (Exception e){
            Log.e("ErrorNav", e.toString());
            e.printStackTrace();
        }
    }
}

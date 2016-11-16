package mn.edu.num.student.timemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final Button bLogout = (Button) findViewById(R.id.bLogout);

        if (((GlobalApplication) this.getApplication()).uid == -1){
            bLogin.setVisibility(View.VISIBLE);
            bRegister.setVisibility(View.VISIBLE);
            bLogout.setVisibility(View.GONE);
        }
        else {
            bLogin.setVisibility(View.GONE);
            bRegister.setVisibility(View.GONE);
            bLogout.setVisibility(View.VISIBLE);
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
    }

    public void set(){
        ((GlobalApplication) this.getApplication()).set(-1,null,null,null,null,0);
        return;
    }
}

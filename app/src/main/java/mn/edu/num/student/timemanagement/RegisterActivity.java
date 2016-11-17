package mn.edu.num.student.timemanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(0xFFFFFFFF);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etSisi = (EditText) findViewById(R.id.etSisi);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etPasswordConf = (EditText) findViewById(R.id.etPasswordConf);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        etUsername.setFilters(new InputFilter[] {
            new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    for (int i = start; i < end; i++)
                        if (Character.isSpaceChar(source.charAt(i))) return "";
                    return null;
                }
            }
        });

        etName.setFilters(new InputFilter[] {
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        for (int i = start; i < end; i++)
                            if (!(Character.isLetter(source.charAt(i)) || Character.isSpaceChar(source.charAt(i)))) return "";
                        return null;
                    }
                }
        });

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!etPassword.getText().toString().equals(etPasswordConf.getText().toString())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Password is not same")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
                else {
                    final String name = etName.getText().toString();
                    final String username = etUsername.getText().toString();
                    final String sisi = etSisi.getText().toString();
                    final String email = etEmail.getText().toString();
                    final String password = etPassword.getText().toString();

                    if (!isEmail(email)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Invalid E-Mail")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                    else {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject("{\"success\": true}");
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setMessage("Registered Successfully")
                                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                        RegisterActivity.this.startActivity(loginIntent);
                                                    }
                                                })
                                                .create()
                                                .show();
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setMessage("Register Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        RegisterRequest registerRequest = new RegisterRequest(name, username, sisi, email, password, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                        queue.add(registerRequest);
                    }
                }
            }
        });
    }

    private boolean isEmail(String email){
        if (email == null) return false;
        else return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isName(String name){
        if (name == null) return false;
        else {
            Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
            return pattern.matcher(name).matches();
        }
    }
}

package mn.edu.num.student.timemanagement;

import android.app.Application;

/**
 * Created by xrek on 11/15/2016.
 */

public class GlobalApplication extends android.app.Application {
    public int uid = -1, type;
    public String name, username, sisi, email;

    public int getID() {
        return uid;
    }

    public int getType() {
        return type;
    }

    public String getname() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getSisi() {
        return sisi;
    }

    public String getEmail() {
        return email;
    }

    public void set(int id, String name, String username, String sisi, String email, int type) {
        this.uid = id;
        this.name = name;
        this.username = username;
        this.sisi = sisi;
        this.email = email;
        this.type = type;
    }
}

package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class BaseActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    void managerFragmentTransaction (int idContainer, Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(idContainer);
        FragmentTransaction transaction = manager.beginTransaction();
        if (transaction.isEmpty()) {
            transaction.add(idContainer, fragment);
        } else {
            transaction.replace(idContainer, fragment);
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }


}

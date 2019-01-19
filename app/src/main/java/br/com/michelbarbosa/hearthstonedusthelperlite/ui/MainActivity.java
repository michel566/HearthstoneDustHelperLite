package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;

public class MainActivity extends FragmentActivity implements CardListener {

    CardFragment cardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardFragment = CardFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_manager, cardFragment)
                .commit();

        //need for listener communication
        cardFragment = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_manager);
    }


    @Override
    public void onClick(String link) {
     /*   if (cardFragment != null && cardFragment.isInLayout()){
            cardFragment.getText(Integer.parseInt(link));
        } */
    }
}

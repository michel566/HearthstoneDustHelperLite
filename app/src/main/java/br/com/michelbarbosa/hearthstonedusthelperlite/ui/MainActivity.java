package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.Util;

public class MainActivity extends FragmentActivity implements CardListener {

    //need for listener communication
    CardFragment cardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get access to the detail view fragment by id - Need this to receive listener communication
        cardFragment = (CardFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_manager);

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        /*

        //receive bundle communication - need comment downline
         CardFragment fragment = CardFragment.newInstance(5,"my title");
         ft.replace(R.id.fragment_manager, fragment);

        */

        // Replace the contents of the container with the new fragment
        ft.add(R.id.fragment_manager, new CardFragment());

        /*

        //start method comunication - need remove upperline
         CardFragment fragment = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_manager);
         fragment.doSomething(ft);

        */
        recoverCardFragmentForTag(savedInstanceState, getSupportFragmentManager());

        // Complete the changes added above
        ft.commit();

    }

/*
    @Override
    protected CardFragment getFragmentInstance(){
        ArrayList<Card> cards = getIntent().getParcelableArrayExtra("link");
        return CardFragment.newInstance(cards);
    }



    */

    protected static void recoverCardFragmentForTag(Bundle savedInstanceState, FragmentManager fm){
        if (savedInstanceState == null){
            // Let's first dynamically add a fragment into a frame container
            fm.beginTransaction()
                    .replace(R.id.fragment_manager, new CardFragment(), "CardFragment")
                    .commit();
        }
    }


    @Override
    public void onClick(String link) {
     /*   if (cardFragment != null && cardFragment.isInLayout()){
            cardFragment.getText(Integer.parseInt(link));
        } */
    }
}

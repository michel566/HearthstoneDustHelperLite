package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;

public class MainActivity extends BaseActivity implements CardListener {

    FormFragment formFragment;
    CardFragment cardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formFragment = new FormFragment();
        cardFragment = new CardFragment();

        managerFragmentTransaction(R.id.formContainer, formFragment);
        managerFragmentTransaction(R.id.cardContainer, cardFragment);
    }

    @Override
    public void onUpdateDeckOnClick(Card card) {
        cardFragment.getAdapter().updateList(formFragment.getCard());
    }
}

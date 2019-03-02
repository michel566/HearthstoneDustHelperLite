package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.os.Bundle;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Result;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.DustGenerator;

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

    @Override
    public void onGenerateDeckDustOnClick() {
        //todo: put this calling in asynctask call
        Result result = new Result(
                DustGenerator.investimentoTotal(cardFragment.getAdapter().getDeck()),
                DustGenerator.quoeficiente(cardFragment.getAdapter().getDeck()),
                DustGenerator.quoeficienteDeInvestimento(cardFragment.getAdapter().getDeck()),
                DustGenerator.classificacaoGeral(cardFragment.getAdapter().getDeck())
        );
        formFragment.setResult(result);
    }

    @Override
    public void onGenerateCardDustOnClick(Card card) {

    }

    @Override
    public void onRemoveCard() {
        onGenerateDeckDustOnClick();
        formFragment.removeCardCounter();
    }

    @Override
    public void onClearDeck() {
        cardFragment.getAdapter().clearDeck();
    }

}

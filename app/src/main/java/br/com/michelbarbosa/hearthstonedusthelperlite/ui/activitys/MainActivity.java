package br.com.michelbarbosa.hearthstonedusthelperlite.ui.activitys;

import android.os.Bundle;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.StaticCard;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Result;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.fragments.CardFragment;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.fragments.FormFragment;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.dustgenerator.DustGenerator;

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
    public void onUpdateDeckOnClick(StaticCard staticCard) {
        cardFragment.getAdapter().updateList(formFragment.getStaticCard());
    }

    @Override
    public void onGenerateDeckDustOnClick() {
        //todo: put this calling in asynctask call
        Result result = new Result(
                DustGenerator.investimentoTotal(cardFragment.getAdapter().getDeck()),
                DustGenerator.quoeficiente(cardFragment.getAdapter().getDeck()),
                DustGenerator.quoeficienteDeInvestimento(cardFragment.getAdapter().getDeck()),
                DustGenerator.classificacaoGeral(cardFragment.getAdapter().getDeck(), cardFragment.getAdapter().getPosition())
        );
        formFragment.setResult(result);
    }

    @Override
    public void onGenerateCardDustOnClick(StaticCard staticCard) {

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

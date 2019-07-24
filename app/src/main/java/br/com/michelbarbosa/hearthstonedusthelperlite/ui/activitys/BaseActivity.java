package br.com.michelbarbosa.hearthstonedusthelperlite.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.michelbarbosa.hearthstonedusthelperlite.MainApp;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.IView;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.Presenter;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.BaseCard;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Info;

public class BaseActivity extends AppCompatActivity implements IView {
    @Inject
    protected Presenter mPresenter;
    @Inject
    protected Picasso picasso;
    @Inject
    Context mContext;
    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApp) getApplication()).getmAppComponent().inject(this);
        mPresenter.setmView(this);
        loadDataAPIModules();
    }

    //Método para carregar os Modulos da API necessarios que serão usados em todo projeto
    private void loadDataAPIModules(){
        //O método getInfo é necessário para carregar os dados de informacao das cartas
        mPresenter.getInfo();
    }

    void managerFragmentTransaction(int idContainer, Fragment fragment) {
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

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        hideProgress();
    }

    @Override
    public void onUpdate(BaseCard card) {

    }

    @Override
    public void onUpdate(List<Card> cards) {

    }

    @Override
    public void onUpdate(Info info) {

    }

    @Override
    public void showProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

}

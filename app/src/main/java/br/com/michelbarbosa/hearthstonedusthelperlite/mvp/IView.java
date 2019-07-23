package br.com.michelbarbosa.hearthstonedusthelperlite.mvp;

import java.util.List;

import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.BaseCard;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Info;

public interface IView {

    public void onComplete();

    public void onError(String error);

    public void onUpdate(BaseCard card);

    public void onUpdate(List<Card> cards);

    public void onUpdate(Info info);

    public void showProgress();

    public void hideProgress();

}

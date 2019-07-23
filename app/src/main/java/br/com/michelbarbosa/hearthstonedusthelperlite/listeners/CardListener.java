package br.com.michelbarbosa.hearthstonedusthelperlite.listeners;

import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.StaticCard;

public interface CardListener {

    void onUpdateDeckOnClick(StaticCard staticCard);

    void onGenerateDeckDustOnClick();

    void onGenerateCardDustOnClick(StaticCard staticCard);

    void onRemoveCard();

    void onClearDeck();
}

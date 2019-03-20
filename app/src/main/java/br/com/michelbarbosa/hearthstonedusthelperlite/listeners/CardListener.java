package br.com.michelbarbosa.hearthstonedusthelperlite.listeners;

import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;

public interface CardListener {

    void onUpdateDeckOnClick(Card card);

    void onGenerateDeckDustOnClick();

    void onGenerateCardDustOnClick(Card card);

    void onRemoveCard();

    void onClearDeck();
}

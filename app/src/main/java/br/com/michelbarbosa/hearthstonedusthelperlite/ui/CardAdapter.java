package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder>{

    private Context context;
    private final List<Card> cards;

    private CardListener listener;

    public CardAdapter(Context context, List<Card> cards, CardListener listener) {
        this.context = context;
        this.cards = cards;
        this.listener = listener;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Card> getCards() {
        return cards;
    }

    public CardListener getListener() {
        return listener;
    }

    public void setListener(CardListener listener) {
        this.listener = listener;
    }

    //Listener e chamado do adapter no fragment para iteragir

    /*
    public void setListener(CardListener listener) {
        this.listener = listener;
    }
    */

    //Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view.
    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card, parent, false));
    }

    //Método que recebe o ViewHolder e a posição da lista.
    // Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder. É onde a mágica acontece!
    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {

        //Crie uma nova instancia aqui da classe CardHolder criada

        //Aponta uma posicao para cada item
        Card card = cards.get(position);
        holder.cardName.setText(card.getNome());


/*
        holder.cardName.setText(String.format(Locale.getDefault(), "%s, %d - %s",
                cards.get(position).getNome(),
                cards.get(position).getRaridade(),
                cards.get(position).getClasse(),
                cards.get(position).getExpansao()
        ));
*/

        holder.addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("teste", "[CardAdapter.onBindViewHolder: ]addCard clicado");
                //(view -> updateItem(position));
            }
        });
        holder.removeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("teste", "[CardAdapter.onBindViewHolder: ]removeCard clicado");
                //(view -> removerItem(position));
            }
        });

    }

    //Método que deverá retornar quantos itens há na lista. Aconselha-se verificar se a lista não está nula como no exemplo,
    // pois ao tentar recuperar a quantidade da lista nula pode gerar um erro em tempo de execução
    @Override
    public int getItemCount() {
        return cards != null ? cards.size() : 0;
    }

    public void updateList(Card card) {
        insertCard(card);
    }

    private void updateCard(int position) {
        Card card = cards.get(position);
        notifyItemChanged(position);
    }

    private void updateCardName(int position, String name) {
        cards.get(position).setNome(name);
        notifyItemChanged(position);
    }

    // Método responsável por inserir um novo usuário na lista
    //e notificar que há novos itens.

    private void insertCard(Card card) {
        cards.add(card);
        notifyItemInserted(getItemCount());
    }


    // Método responsável por atualizar um usuário já existente na lista.
    private void updateItem(int position) {
        Card card = cards.get(position);
        notifyItemChanged(position);
    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        cards.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cards.size());
    }

    //ViewHolder que irá relacionar componentes visuais do Layout ao código Java.
    public class CardHolder extends RecyclerView.ViewHolder {

        public TextView cardName;
        public ImageButton addCard;
        public ImageButton removeCard;

        public CardHolder(View itemView) {
            super(itemView);
            cardName = itemView.findViewById(R.id.card_name);
            addCard = itemView.findViewById(R.id.add_card);
            removeCard = itemView.findViewById(R.id.remove_card);

            cardName.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("teste", "[CardHolder.CardHolder(): ]card name clicado");
                }
            });

        }

    }

}

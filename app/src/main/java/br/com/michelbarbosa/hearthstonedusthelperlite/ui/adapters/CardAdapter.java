package br.com.michelbarbosa.hearthstonedusthelperlite.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.StaticCard;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.UIUtil;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder>{

    private Context context;
    private final List<StaticCard> deck;

    private int position;

    private CardListener listener;

    public CardAdapter(Context context, List<StaticCard> deck) {
        this.context = context;
        this.deck = deck;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<StaticCard> getDeck() {
        return deck;
    }

    public CardListener getListener() {
        return listener;
    }

    public void setListener(CardListener listener) {
        this.listener = listener;
    }


    //Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view.
    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card, parent, false);
        return new CardHolder(view);
    }

    //Método que recebe o ViewHolder e a posição da lista.
    // Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder. É onde a mágica acontece!
    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, final int position) {
        //Define a posicao atual do elemento
        setPosition(position);

        //Crie uma nova instancia aqui da classe CardHolder criada

        //Aponta uma posicao para cada item
        final StaticCard staticCard = deck.get(position);
        UIUtil.setBackgroundColor(holder.cardLayout, staticCard);
        holder.card_name.setText(staticCard.getNome());
        //holder.card_class.setText(staticCard.getClasse());
       // holder.card_rarity.setText(staticCard.getRaridade());
        holder.card_rarity.setBackgroundResource(UIUtil.getImageGemToRarity(staticCard.getRaridade()));
        holder.card_collection.setText(staticCard.getExpansao());

        holder.removeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveCard();
                Log.i("teste", "[CardAdapter.onBindViewHolder: ]removeCardCounter clicado");
                //(view -> removerItem(position));
                removeCard(position);
            }
        });

    }

    //Método que deverá retornar quantos itens há na lista. Aconselha-se verificar se a lista não está nula como no exemplo,
    // pois ao tentar recuperar a quantidade da lista nula pode gerar um erro em tempo de execução
    @Override
    public int getItemCount() {
        return deck != null ? deck.size() : 0;
    }

    public void updateList(StaticCard staticCard) {
        insertCard(staticCard);
    }

    private void updateCard(int position) {
        StaticCard staticCard = deck.get(position);
        notifyItemChanged(position);
    }

    // Método responsável por inserir um novo usuário na lista
    //e notificar que há novos itens.
    private void insertCard(StaticCard staticCard) {
        deck.add(staticCard);
        notifyItemInserted(getItemCount());
    }

    // Método responsável por atualizar um usuário já existente na lista.
    private void updateItem(int position) {
        StaticCard staticCard = deck.get(position);
        notifyItemChanged(position);
    }

    // Método responsável por remover um usuário da lista.
    private void removeCard(int position) {
        deck.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, deck.size());
    }

    public void clearDeck(){
        deck.clear();
        notifyDataSetChanged();
    }

    //ViewHolder que irá relacionar componentes visuais do Layout ao código Java.
    public class CardHolder extends RecyclerView.ViewHolder {

        TextView card_name;
       // TextView card_class;
        ImageView card_rarity;
        TextView card_collection;

        ImageButton removeCard;
        RelativeLayout cardLayout;

        CardHolder(View itemView) {
            super(itemView);
            card_name = itemView.findViewById(R.id.card_name);
          ///  card_class = itemView.findViewById(R.id.card_class);
            card_rarity = itemView.findViewById(R.id.card_rarity);
            card_collection = itemView.findViewById(R.id.card_collection);

            removeCard = itemView.findViewById(R.id.remove_card);
            cardLayout = itemView.findViewById(R.id.card_selected);

            card_name.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("teste", "[CardHolder.CardHolder(): ]card name clicado");
                }
            });

        }

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


}

package br.com.michelbarbosa.hearthstonedusthelperlite.ui.holders;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.adapters.RecyclerViewClickListener;

public class TestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final RecyclerViewClickListener viewHolderListener;
    private TextView nameTextView;

    public TestViewHolder(@NonNull View view, final RecyclerViewClickListener viewHolderListener) {
        super(view);
        this.viewHolderListener = viewHolderListener;
        this.nameTextView = view.findViewById(R.id.cardName);

        //Por algum motivo preciso chamar o evento do clique do listener dentro do construtor...
        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolderListener.onClick(nameTextView, getAdapterPosition());
            }
        });
    }

    public void bindItem(Card card) {
        if (!TextUtils.isEmpty(card.name)) {
            nameTextView.setText(card.name);
        }
    }

    @Override
    public void onClick(View v) {
    }
}

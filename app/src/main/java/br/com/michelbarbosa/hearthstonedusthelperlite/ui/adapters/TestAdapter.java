package br.com.michelbarbosa.hearthstonedusthelperlite.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.List;

import javax.inject.Inject;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.holders.TestViewHolder;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class TestAdapter extends RecyclerView.Adapter<TestViewHolder> {

    private final LayoutInflater layoutInflater;
    private final RecyclerViewClickListener testAdapterListener;
    private List<Card> items = emptyList();

    @Inject
    public TestAdapter(LayoutInflater layoutInflater, RecyclerViewClickListener testAdapterListener) {
        this.layoutInflater = layoutInflater;
        this.testAdapterListener = testAdapterListener;
    }

    public void setData(List<Card> items) {
        this.items = unmodifiableList(items);
        notifyDataSetChanged();
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TestViewHolder(layoutInflater.inflate(R.layout.card_test, null), testAdapterListener);
    }

    @Override
    public void onBindViewHolder(TestViewHolder testViewHolder, int i) {
        testViewHolder.bindItem(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Card getCard(int poistion) {
        return items.get(poistion);
    }

}

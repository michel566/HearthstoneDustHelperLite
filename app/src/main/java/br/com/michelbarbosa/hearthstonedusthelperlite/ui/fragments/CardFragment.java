package br.com.michelbarbosa.hearthstonedusthelperlite.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.StaticCard;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.adapters.CardAdapter;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.UIUtil;

public class CardFragment extends Fragment {

    private CardListener listener;
    private ArrayList<StaticCard> staticCards;
    private CardAdapter adapter;
    private StaticCard staticCard;

    public CardFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 0. inflate main fragment in the Adapter
        View mainView = inflater.inflate(R.layout.fragment_card_list, container, false);

        // 1. get a reference to recyclerView
        RecyclerView recyclerViewofCards = mainView.findViewById(R.id.card_recycleView);

        // 2. set layoutManger to do a list, and set the render
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewofCards.setLayoutManager(layoutManager);

        // 3. create an adapter, and populate them
        adapter = new CardAdapter(getContext(), new ArrayList<StaticCard>(0));

        // 4. set adapter, and add objects to list
        recyclerViewofCards.setAdapter(adapter);

        // 5. set listener in adapter
        adapter.setListener(listener);

        // 6. set divider to separate the adapters .addItemDecoration(
        addDividerToCard(recyclerViewofCards, getContext());

        // 7. set item animator to DefaultAnimator
        recyclerViewofCards.setItemAnimator(new DefaultItemAnimator());

        return mainView;
    }

    private void setRenderReverseRecycler(LinearLayoutManager layoutManager) {
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
    }

    private void addDividerToCard(RecyclerView recyclerView, Context context) {
        // Configurando um dividr entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //is a bundle communicator atribuitions
         /*
         int SomeInt = getArguments().getInt("someInt", 0);
         String someTitle = getArguments().getString("someTitle", "");
         */

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof CardListener) {
            listener = (CardListener) context;
        } else {
            throw new ClassCastException(context.toString() + "must implement CardFragment.CardListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    static ArrayList<StaticCard> populateTestItens() {
        ArrayList<StaticCard> staticCardList = new ArrayList<>();

        staticCardList.add(0, new StaticCard("carta 1", "comum", "Paladino", "expansao1"));
        staticCardList.add(1, new StaticCard("carta 2", "rara", "Mago", "expansao3"));
        staticCardList.add(2, new StaticCard("carta 3", "rara", "Neutro", "expansao1"));
        staticCardList.add(3, new StaticCard("carta 4", "comum", "Ladino", "expansao2"));
        staticCardList.add(4, new StaticCard("carta 5", "lendaria", "Druida", "expansao2"));
        staticCardList.add(5, new StaticCard("carta 6", "comum", "Neutro", "expansao4"));
        staticCardList.add(6, new StaticCard("carta 7", "epica", "Bruxo", "expansao3"));
        staticCardList.add(7, new StaticCard("carta 8", "comum", "Xamã", "expansao4"));
        staticCardList.add(8, new StaticCard("carta 9", "comum", "Caçador", "expansao4"));
        staticCardList.add(9, new StaticCard("carta 10", "comum", "Ladino", "expansao1"));

        return staticCardList;

    }


    public CardAdapter getAdapter() {
        return adapter;
    }

}

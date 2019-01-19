package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.Util;

public class CardFragment extends Fragment implements CardListener {

    private CardListener listener;
    private ArrayList<Card> cards;
    private CardAdapter adapter;

    public static CardFragment newInstance(){
        return new CardFragment();
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
        CardAdapter adapter = new CardAdapter(getContext(), populateTestItens(), listener);

        // 4. set adapter, and add objects to list
        recyclerViewofCards.setAdapter(adapter);

        // 5. set listener in adapter
        adapter.setListener(this);

        // 6. set divider to separate the adapters .addItemDecoration(
        addDividerToCard(recyclerViewofCards, getContext());

        // 7. set item animator to DefaultAnimator
        recyclerViewofCards.setItemAnimator(new DefaultItemAnimator());

        // 8. set the Listener
        adapter.setListener(this);

        return mainView;
    }

    private void setRenderReverseRecycler(LinearLayoutManager layoutManager){
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
    }

    private void addDividerToCard(RecyclerView recyclerView, Context context){
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


    // Dispara o metodo implementado pela Activity

    /*
    public void updateDetail(String link) {
        listener.onClick(link);
    }
    */

    /*
    @Override
    public void onClick(View view) {
        Log.i("testeFragment", "fragment response");

    }
    */

    //
    static ArrayList<Card> populateTestItens() {

        ArrayList<Card> cardList = new ArrayList<>();

        cardList.add(0, new Card("carta 1", "comum", "Neutro", "expansao1"));
        cardList.add(1, new Card("carta 2", "rara", "Guerreiro", "expansao3"));
        cardList.add(2, new Card("carta 3", "rara", "Mago", "expansao1"));
        cardList.add(3, new Card("carta 4", "comum", "Ladino", "expansao2"));
        cardList.add(4, new Card("carta 5", "lendaria", "Druida", "expansao2"));
        cardList.add(5, new Card("carta 6", "comum", "Neutro", "expansao4"));
        cardList.add(6, new Card("carta 7", "epica", "Ladino", "expansao3"));
        cardList.add(7, new Card("carta 8", "comum", "Xamã", "expansao4"));
        cardList.add(8, new Card("carta 9", "comum", "Neutro", "expansao4"));
        cardList.add(9, new Card("carta 10", "comum", "Paladino", "expansao1"));

        return cardList;

    }

    static String obterClasse(ArrayList<Card> cardList, int position){
        return cardList.get(position).getClasse();
    }

    static void setCardColor(RelativeLayout layout, String classe){
        layout.setBackgroundResource(Util.getBackgroundColorToClass(classe));
    }


     // This is a bundle communicator
    // DemoFragment.newInstance(5, "Hello");

    static CardFragment newInstance(int someInt, String someTitle) {

        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        args.putString("someTitle", someTitle);
        fragment.setArguments(args);

        return fragment;
    }

    //This is a method communicator
    void doSomething(FragmentTransaction ft) {

        //This codelines is a only example with you can made

        /*
        CardFragment fragment = CardFragment.newInstance(5,"my title");
        ft.replace(R.id.fragment_manager, fragment);
        */
    }

    @Override
    public void onClick(String link) {
        Log.i("teste", "[CardFragment.onCLick: ]Fragment.onCLick clicado");
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public CardAdapter getAdapter() {
        return adapter;
    }
/*
    public RecyclerView getCardView(View view) {
        return cardView = view.findViewById(R.id.card_recycleView);
    }

    public void setCardView(RecyclerView cardView) {
        this.cardView = cardView;
    }
*/
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setAdapter(CardAdapter adapter) {
        this.adapter = adapter;
    }


}
package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;

public class CardFragment extends Fragment implements CardListener {

    private CardListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 0. inflate main fragment in the Adapter
        View mainView = inflater.inflate(R.layout.fragment_card_list, container, false);

        // 1. get a reference to recyclerView
        RecyclerView rv = mainView.findViewById(R.id.card_recycleView);
/*
        // 2. set layoutManger
        recyclerViewofCards.setLayoutManager(new LinearLayoutManager(getContext()));

        // 3. create an adapter
        CardAdapter adapter = new CardAdapter(cardList);

        // 4. set adapter
        recyclerViewofCards.setAdapter(adapter);

        // 5. set divider to separate the adapters .addItemDecoration(
        recyclerViewofCards.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        // 6. set item animator to DefaultAnimator
        recyclerViewofCards.setItemAnimator(new DefaultItemAnimator());

        // 7. set listener in adapter
        adapter.setListener(this);
*/

        // Configurando o gerenciador de layout para ser uma lista.
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        // Depende do metodo populateTestItens acima
        CardAdapter adapter = new CardAdapter(getContext(), populateTestItens(), listener);

        // Adiciona o adapter que irá anexar os objetos à lista.
        rv.setAdapter(adapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        // Configurando o listener
        //adapter.setListener(this);

        //Animation
        rv.setItemAnimator(new DefaultItemAnimator());

        // setupRecycler(recyclerViewofCards);

        return mainView;
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
    private static ArrayList<Card> populateTestItens(){

        ArrayList<Card> cardList = new ArrayList<>();

        cardList.add(0, new Card("carta 1", "comum", "neutra", "expansao1"));
        cardList.add(1, new Card("carta 2", "rara", "warrior", "expansao3"));
        cardList.add(2, new Card("carta 3", "rara", "mage", "expansao1"));
        cardList.add(3, new Card("carta 4", "comum", "rogue", "expansao2"));
        cardList.add(4, new Card("carta 5", "lendaria", "druid", "expansao2"));
        cardList.add(5, new Card("carta 6", "comum", "neutra", "expansao4"));
        cardList.add(6, new Card("carta 7", "epica", "rogue", "expansao3"));
        cardList.add(7, new Card("carta 8", "comum", "shaman", "expansao4"));
        cardList.add(8, new Card("carta 9", "comum", "neutra", "expansao4"));
        cardList.add(9, new Card("carta 10", "comum", "priest", "expansao1"));

        return cardList;

    }

    private void setupRecycler(RecyclerView rv){


    }


    // This is a bundle communicator
    // DemoFragment.newInstance(5, "Hello");

    static CardFragment newInstance(int someInt, String someTitle){

        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        args.putString("someTitle", someTitle);
        fragment.setArguments(args);

        return fragment;
    }

    //This is a method communicator
    void doSomething(FragmentTransaction ft){

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
}

package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.Util;

public class FormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String CARD = "card";
    // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Card card;
   // private String mParam2;

    //para testes
    private List<Card> deck = new ArrayList<>();
    private CardListener listener;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String card) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(CARD, card);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            card = getArguments().getParcelable(CARD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView editName = view.findViewById(R.id.editName);
        final Spinner spRarity = view.findViewById(R.id.spRarity);
        final Spinner spClass = view.findViewById(R.id.spClass);
        final Spinner spCollection = view.findViewById(R.id.spCollection);

        ImageView addCardButton = view.findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = cardGenerator(editName.getText().toString(), spRarity.getSelectedItem().toString(), spClass.getSelectedItem().toString(), spCollection.getSelectedItem().toString());
                setCard(card);
                Util.outputCardLog("FormFragment",card);
                deck.add(card);
                listener.onUpdateDeckOnClick(card);
            }
        });

        ImageView dustDeckButton = view.findViewById(R.id.dustDeckButton);
        dustDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.outputDeckLog(deck);
            }
        });

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
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public Card cardGenerator(String nome, String raridade, String classe, String colecao){
        return new Card(nome, raridade, classe, colecao);
    }

    private void setCard(Card card){
        this.card = card;
    }

    public Card getCard(){
        return card;
    }

}

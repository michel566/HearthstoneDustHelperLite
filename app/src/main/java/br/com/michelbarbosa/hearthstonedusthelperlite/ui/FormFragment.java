package br.com.michelbarbosa.hearthstonedusthelperlite.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Result;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.Util;

public class FormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private static final String CARD = "card";

    // TODO: Rename and change types of parameters
    private Card card;
    private Result result;

    private static int count = 0;

    private CardListener listener;

    private TextView tvCount;
    private TextView tvTotalInvestment;
    private TextView tvQuoeficent;
    private TextView tvQuoeficentOfInvestment;
    private TextView tvTotalDust;

    public FormFragment() {
        // Required empty public constructor
    }

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

        //todo: criar um metodo para poder bloquear apos a primeira escolha de uma classe do game para limitar somente as escolhas (classe escolhida, neutra, classica), assim que remover todas as cartas, volta ao normal
        final Spinner spClass = view.findViewById(R.id.spClass);
        final Spinner spCollection = view.findViewById(R.id.spCollection);

        tvCount = view.findViewById(R.id.tvCount);

        tvTotalInvestment = view.findViewById(R.id.tvTotalInvestment);
        tvQuoeficent = view.findViewById(R.id.tvQuoeficent);
        tvQuoeficentOfInvestment = view.findViewById(R.id.tvQuoeficentOfInvestment);
        tvTotalDust = view.findViewById(R.id.tvTotalDust);

        ImageView addCardButton = view.findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 30){
                    Card card = cardGenerator(editName.getText().toString(), spRarity.getSelectedItem().toString(), spClass.getSelectedItem().toString(), spCollection.getSelectedItem().toString());
                    setCard(card);
                    Util.outputCardLog("FormFragment - Card added ->",card);
                    //deck.add(card);
                    listener.onUpdateDeckOnClick(card);
                    count++;
                }

                updateCount();
            }
        });


        ImageView dustDeckButton = view.findViewById(R.id.dustDeckButton);
        dustDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGenerateDeckDustOnClick();
                updateTextViews(false);
            }
        });

        ImageView recycleButton = view.findViewById(R.id.recycleButton);
        recycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClearDeck();
                updateTextViews(true);
                count = 0;
                updateCount();
            }
        });
    }

    private void updateCount(){
        tvCount.setText(String.format(Locale.getDefault(), "%d", getCount()));
    }

    private void updateTextViews(boolean toClean){
        if(toClean){
            tvTotalInvestment.setText(Util.outputLocaleFormat(getResources().getString(R.string.investimento_total), 0));
            tvQuoeficent.setText(Util.outputLocaleFormat(getResources().getString(R.string.quoeficiente), 0));
            tvQuoeficentOfInvestment.setText(Util.outputLocaleFormat(getResources().getString(R.string.quoeficiente_de_investimento), 0));
            tvTotalDust.setText(Util.outputLocaleFormat(getResources().getString(R.string.investimento), ""));
            listener.onGenerateDeckDustOnClick();
        }
        tvTotalInvestment.setText(Util.outputLocaleFormat(getResources().getString(R.string.investimento_total), result.getInvestimentoTotal()));
        tvQuoeficent.setText(Util.outputLocaleFormat(getResources().getString(R.string.quoeficiente), result.getQuoeficiente()));
        tvQuoeficentOfInvestment.setText(Util.outputLocaleFormat(getResources().getString(R.string.quoeficiente_de_investimento), result.getQuoefDeInvestimento()));
        tvTotalDust.setText(Util.outputLocaleFormat(getResources().getString(R.string.investimento), result.getInvestimento()));
    }

    private Card cardGenerator(String nome, String raridade, String classe, String colecao){
        return new Card(nome, raridade, classe, colecao);
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

    public void removeCardCounter(){
        count--;
        updateCount();
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static int getCount() {
        return count;
    }

    private void setCard(Card card){
        this.card = card;
    }

    public Card getCard(){
        return card;
    }

}

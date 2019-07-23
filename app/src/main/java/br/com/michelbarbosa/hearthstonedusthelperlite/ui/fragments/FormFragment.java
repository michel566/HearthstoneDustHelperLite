package br.com.michelbarbosa.hearthstonedusthelperlite.ui.fragments;

import android.content.Context;
import android.content.Intent;
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

import java.util.Locale;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.listeners.CardListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.StaticCard;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Result;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.activitys.APIResponseTestActivity;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.UIUtil;

public class FormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private static final String CARD = "staticCard";

    // TODO: Rename and change types of parameters
    private StaticCard staticCard;
    private Result result;

    private static int count = 0;

    private CardListener listener;

    private TextView tvCount;
    private TextView tvTotalInvestment;
    private TextView tvQuoeficent;
    private TextView tvQuoeficentOfInvestment;
    private TextView tvTotalDust;

    private ImageView ivJumpToTest;

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
            staticCard = getArguments().getParcelable(CARD);
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
        //todo: olhar a url >>> https://stackoverflow.com/questions/47493043/fragment-adding-image-in-spinner

        final Spinner spClass = view.findViewById(R.id.spClass);
        final Spinner spCollection = view.findViewById(R.id.spCollection);

        //API Teste:
        ivJumpToTest = view.findViewById(R.id.iv_jumpToTest);

        ivJumpToTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirAPIResponse();
            }
        });

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
                    StaticCard staticCard = cardGenerator(editName.getText().toString(), spRarity.getSelectedItem().toString(), spClass.getSelectedItem().toString(), spCollection.getSelectedItem().toString());
                    setStaticCard(staticCard);
                    UIUtil.outputCardLog("FormFragment - StaticCard added ->", staticCard);
                    //deck.add(staticCard);
                    listener.onUpdateDeckOnClick(staticCard);
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

    private void exibirAPIResponse() {
        Intent intent = new Intent(getActivity(), APIResponseTestActivity.class);
        startActivity(intent);
    }

    private void updateCount(){
        tvCount.setText(String.format(Locale.getDefault(), "%d", getCount()));
    }

    private void updateTextViews(boolean toClean){
        if(toClean){
            tvTotalInvestment.setText(UIUtil.outputLocaleFormat(getResources().getString(R.string.investimento_total), 0));
            tvQuoeficent.setText(UIUtil.outputLocaleFormat(getResources().getString(R.string.quoeficiente), 0));
            tvQuoeficentOfInvestment.setText(UIUtil.outputLocaleFormat(getResources().getString(R.string.quoeficiente_de_investimento), 0));
            tvTotalDust.setText(UIUtil.outputLocaleFormat(getResources().getString(R.string.investimento), ""));
            listener.onGenerateDeckDustOnClick();
        }
        tvTotalInvestment.setText(UIUtil.outputLocaleFormat(getResources().getString(R.string.investimento_total), result.getInvestimentoTotal()));
        tvQuoeficent.setText(UIUtil.outputLocaleFormat(getResources().getString(R.string.quoeficiente), result.getQuoeficiente()));
        tvQuoeficentOfInvestment.setText(UIUtil.outputLocaleFormat(getResources().getString(R.string.quoeficiente_de_investimento), result.getQuoefDeInvestimento()));
        tvTotalDust.setText(UIUtil.outputLocaleFormat(getResources().getString(R.string.investimento), result.getInvestimento()));
    }

    private StaticCard cardGenerator(String nome, String raridade, String classe, String colecao){
        return new StaticCard(nome, raridade, classe, colecao);
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

    private void setStaticCard(StaticCard staticCard){
        this.staticCard = staticCard;
    }

    public StaticCard getStaticCard(){
        return staticCard;
    }

}

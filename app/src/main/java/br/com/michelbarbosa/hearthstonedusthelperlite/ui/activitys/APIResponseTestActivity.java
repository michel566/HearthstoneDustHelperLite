package br.com.michelbarbosa.hearthstonedusthelperlite.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Card;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.Info;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.SpinnerClassItem;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.adapters.RecyclerViewClickListener;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.adapters.SpinnerClassAdapter;
import br.com.michelbarbosa.hearthstonedusthelperlite.ui.adapters.TestAdapter;

public class APIResponseTestActivity extends BaseActivity {

    private RecyclerView recyclerView;
    public Card card;
    private TestAdapter adapter;

    private ArrayList<SpinnerClassItem> spClassItemList;
    private SpinnerClassAdapter spAdapter;
    private Spinner spClass;

    private TextView tvCardId, tvRarity, tvCardSet, tvName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiresponse_test);

        tvCardId = findViewById(R.id.tvCardId);
        //O nome no xml ta igual a do formulario original, mas aqui e tv_rarity
        tvRarity = findViewById(R.id.tv_rarity);
        tvCardSet = findViewById(R.id.tvCardSet);
        //O nome no xml ta igual a do formulario original, mas aqui e tv_name
        tvName = findViewById(R.id.tv_name);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_test);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        spClass = findViewById(R.id.spClassName);
        spAdapter = new SpinnerClassAdapter(this, spClassItemList);
        spClass.setAdapter(spAdapter);

        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerClassItem clickedSpClassItem = (SpinnerClassItem) parent.getItemAtPosition(position);
                String clickedClassName = clickedSpClassItem.getClassName();
                Toast.makeText(APIResponseTestActivity.this, clickedClassName + " selected", Toast.LENGTH_SHORT).show();
                Log.e("spinner selected ->> ", clickedClassName);

                loadCardListByClass(clickedSpClassItem.getClassName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //Qualquer evento chamado la no construtor do viewHolder do adapter, aqui será chamado antes de lá
    //Aqui irá capturar os dados da lista e preenchera o formulario abaixo
    RecyclerViewClickListener mListener = new RecyclerViewClickListener() {
        public void onClick(View v, int poistion) {
            Card card = adapter.getCard(poistion);
            setCardData(card);
        }
    };

    private void setCardData(Card card) {
        tvCardId.setText(String.format(" %s", card.cardId));
        tvRarity.setText(String.format(" %s", card.rarity));
        //         tvRarity.setText(String.format(getResources().getString(R.string.attack), card.rarity)); todo: para usar a string correspondente concatenada ao dado
        tvCardSet.setText(String.format(" %s", card.cardSet));
        tvName.setText(String.format(" %s", card.name));
    }

    //Esses dois métodos update obrigatoriamente precisam ser sobrescritos pois estao carregando as cartas por classe e a informação do hs
    @Override
    public void onUpdate(List<Card> cards) {
        super.onUpdate(cards);
        adapter = new TestAdapter(getLayoutInflater(), mListener);
        adapter.setData(cards);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onUpdate(Info info) {
        super.onUpdate(info);
        Log.e("classes ->> ", info.classes.toString());
        Log.e("classes qt ->> ", String.valueOf(info.classes.size()));

        initSpinnerListAdapter(info.classes);
    }

    private void initSpinnerListAdapter(ArrayList<String> classes) {
        //Aqui irá popular a lista personalizada no spinner
        spClassItemList = new ArrayList<>();

        for (int i = 0; i < classes.size(); i++) {
            spClassItemList.add(new SpinnerClassItem(classes.get(i), getImageListforClasses(classes.get(i))));
        }
    }

    private int getImageListforClasses(String className) {
        switch (className) {
            case "Warrior":
                return R.drawable.icon_warrior;
            case "Mage":
                return R.drawable.icon_mage;
            case "Druid":
                return R.drawable.icon_druid;
            case "Paladin":
                return R.drawable.icon_paladin;
            case "Hunter":
                return R.drawable.icon_hunter;
            case "Rogue":
                return R.drawable.icon_rogue;
            case "Shaman":
                return R.drawable.icon_shaman;
            case "Priest":
                return R.drawable.icon_priest;
            case "Warlock":
                return R.drawable.icon_warlock;
            default:
                return R.mipmap.ic_launcher;
        }
    }

    private void loadCardListByClass(String clickedClassName) {
        //O método getCardByClass é necessário para carregar os dados de informacao das cartas
        mPresenter.getCardByClass(clickedClassName);
    }

}

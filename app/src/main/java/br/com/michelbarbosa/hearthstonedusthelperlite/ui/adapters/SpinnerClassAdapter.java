package br.com.michelbarbosa.hearthstonedusthelperlite.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.SpinnerClassItem;
import br.com.michelbarbosa.hearthstonedusthelperlite.utils.UIUtil;

import static java.util.Collections.checkedNavigableMap;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class SpinnerClassAdapter extends ArrayAdapter<SpinnerClassItem> {

    private List<String> infoList = emptyList();

    public SpinnerClassAdapter(Context context, ArrayList<SpinnerClassItem> spClassItem) {
        super(context, 0, spClassItem);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    public void setData(List<String> infoList) {
        this.infoList = unmodifiableList(infoList);
        notifyDataSetChanged();
    }


    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_test, parent, false
            );
        }

        ConstraintLayout layout = convertView.findViewById(R.id.spinnerClassLayout);
        ImageView imClassLogo = convertView.findViewById(R.id.imClassLogo);
        TextView tvClassName = convertView.findViewById(R.id.tvClassName);

        SpinnerClassItem currentItem = getItem(position);

        if (currentItem != null) {
            imClassLogo.setImageResource(currentItem.getClassIcon());
            tvClassName.setText(currentItem.getClassName());
            UIUtil.setBackgroundColor(layout, currentItem.getClassName());
        }

        return convertView;
    }
/*
    private int getBackgroundForClass(String className) {
        switch (className){
            case "Warrior" : return R.color.warrior;
            case "Mage" : return R.color.mage;
            case "Druid" : return R.color.druid;
            case "Paladin" : return R.color.paladin;
            case "Hunter" : return R.color.hunter;
            case "Rogue" : return R.color.rogue;
            case "Shaman" : return R.color.shaman;
            case "Priest" : return R.color.priest;
            case "Warlock" : return R.color.warlock;
            default: return R.mipmap.ic_launcher;
        }
    }
*/
}

package br.com.michelbarbosa.hearthstonedusthelperlite.utils;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.Locale;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model.StaticCard;

public class UIUtil {

    public static void setBackgroundColor(RelativeLayout layout, StaticCard staticCard) {
        layout.setBackgroundResource(UIUtil.pegarClassePorCor(staticCard.getClasse()));
    }

    public static void setBackgroundColor(ConstraintLayout layout, String className) {
        layout.setBackgroundResource(UIUtil.getBackgroundColorToClass(className));
    }

    private static int pegarClassePorCor(String classe) {
        if (classe != null) {
            switch (classe) {
                case ("Paladino"):
                    return R.color.paladinColor;
                case ("Druida"):
                    return R.color.druidColor;
                case ("Caçador"):
                    return R.color.hunterColor;
                case ("Mago"):
                    return R.color.mageColor;
                case ("Guerreiro"):
                    return R.color.warriorColor;
                case ("Sacerdote"):
                    return R.color.priestColor;
                case ("Bruxo"):
                    return R.color.warlockColor;
                case ("Xamã"):
                    return R.color.shamanColor;
                case ("Ladino"):
                    return R.color.rogueColor;
                case ("Neutro"):
                    return R.color.neutralColor;
                default:
                    return R.color.colorPrimary;
            }
        } else {
            return R.color.colorPrimary;
        }
    }

    private static int getBackgroundColorToClass(String classe) {
        if (classe != null) {
            switch (classe) {
                case ("Paladin"):
                    return R.color.paladinColor;
                case ("Druid"):
                    return R.color.druidColor;
                case ("Hunter"):
                    return R.color.hunterColor;
                case ("Mage"):
                    return R.color.mageColor;
                case ("Warrior"):
                    return R.color.warriorColor;
                case ("Priest"):
                    return R.color.priestColor;
                case ("Warlock"):
                    return R.color.warlockColor;
                case ("Shaman"):
                    return R.color.shamanColor;
                case ("Rogue"):
                    return R.color.rogueColor;
                case ("Neutral"):
                    return R.color.neutralColor;
                case ("Dream"):
                    return R.color.dreamColor;
                case ("Death Knight"):
                    return R.color.dkColor;
                default:
                    return R.color.colorPrimary;
            }
        } else {
            return R.color.colorPrimary;
        }
    }


    public static int getImageGemToRarity(String raridade){

        if(raridade != null){
            switch (raridade){
                case ("Comum"): return R.drawable.commongem;
                case ("Raro"): return R.drawable.raregem;
                case ("Épico"): return R.drawable.epicgem;
                case ("Lendário"): return R.drawable.legendarygem;
            }
        } else {
            return 0;
        }
        return 0;
    }

    public static void outputCardLog(String tag, StaticCard staticCard){
        Log.d(tag, "staticCard: " + staticCard.getNome() + " / " + staticCard.getRaridade() + " / " + staticCard.getClasse() + " / " + staticCard.getExpansao());
    }

    public static void outputTextLog(String tag, String value){
        Log.d(tag, value);
    }

    public static void outputDeckLog(List<StaticCard> deck){
        StaticCard staticCard;
        for(int i = 0; i < deck.size(); i++){
            staticCard = deck.get(i);
            Log.d("outputDeckLog", "staticCard: " + staticCard.getNome() + " / " + staticCard.getRaridade() + " / " + staticCard.getClasse() + " / " + staticCard.getExpansao());
        }
    }

    public static String outputLocaleFormat(String text, double value){
        if (String.valueOf(value).equals("NaN")){
            return String.format(Locale.getDefault(), text + " %s", "0");
        }
        return String.format(Locale.getDefault(), text + " %s", String.valueOf(value));
    }

    public static String outputLocaleFormat(String text, String value){
        return String.format(Locale.getDefault(), text + " %s", value);
    }


}

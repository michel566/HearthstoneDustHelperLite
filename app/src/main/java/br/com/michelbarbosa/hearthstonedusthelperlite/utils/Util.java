package br.com.michelbarbosa.hearthstonedusthelperlite.utils;

import android.util.Log;
import java.util.List;
import java.util.Locale;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;
import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;

public class Util {

    public static int getBackgroundColorToClass(String classe) {

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

    public static void outputCardLog(String tag, Card card){
        Log.d(tag, "card: " + card.getNome() + " / " + card.getRaridade() + " / " + card.getClasse() + " / " + card.getExpansao());
    }

    public static void outputDeckLog(List<Card> deck){
        Card card;
        for(int i = 0; i < deck.size(); i++){
            card = deck.get(i);
            Log.d("outputDeckLog", "card: " + card.getNome() + " / " + card.getRaridade() + " / " + card.getClasse() + " / " + card.getExpansao());
        }
    }

    public static String outputLocaleFormat(String text, double value){
        return String.format(Locale.getDefault(), text + " %s", String.valueOf((int) value));
    }

    public static String outputLocaleFormat(String text, String value){
        return String.format(Locale.getDefault(), text + " %s", value);
    }


}

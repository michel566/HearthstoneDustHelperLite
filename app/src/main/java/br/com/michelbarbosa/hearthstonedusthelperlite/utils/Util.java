package br.com.michelbarbosa.hearthstonedusthelperlite.utils;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import br.com.michelbarbosa.hearthstonedusthelperlite.R;

public class Util {

    public static int getInvestimentoTotal;


    public static int getBackgroundColorToClass(String classe){

        if (classe != null){
                switch (classe){
                    case ("Paladino") : return R.color.paladinColor ;
                    case ("Druida") : return R.color.druidColor;
                    case ("Caçador") : return R.color.hunterColor;
                    case ("Mago") : return R.color.mageColor;
                    case ("Guerreiro") : return R.color.warriorColor;
                    case ("Sacerdote") : return R.color.priestColor;
                    case ("Bruxo") : return R.color.warlockColor;
                    case ("Xamã") : return R.color.shamanColor;
                    case ("Ladino") : return R.color.rogueColor;
                    default: return R.color.colorPrimary;
            }
        } else {
            return R.color.colorPrimary;
        }
    }

    private static double getPo(String carta){
        long valor = 40;
        if(carta != null){
            switch (carta){
                case "comum": return valor;
                case "rara": return valor * 2.5;
                case "epica": return valor * 10;
                case "lendaria": return valor * 40;
            }
        } else {
            return valor;
        }
        return valor;
    }

    private static long quoeficienteCartasNeutras(int comum, int rara, int epica, int lendaria){
        return comum + (rara *= 2) + (epica *= 3) + (lendaria *=5);
    }

    private static long quoeficienteCartasdeClasse(int comum, int rara, int epica, int lendaria){
        return comum + rara + epica + lendaria;
    }

    private static double quoeficientePorExpansao(String expansao, double valor){
        if(expansao != null){
            switch (expansao){
                case "Classic": return valor * 12;
                case "Journey to Un'Goro": return valor;
                case "Kobolds & Catacombs": return valor * 10;
                case "lendaria": return valor * 40;
            }
        } else {
            return valor;
        }
        return valor;
    }


}

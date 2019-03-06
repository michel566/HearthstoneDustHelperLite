package br.com.michelbarbosa.hearthstonedusthelperlite.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import br.com.michelbarbosa.hearthstonedusthelperlite.model.Card;

public class DustGenerator {

    public static double investimentoTotal(List<Card> deck) {
        double totalValue = 0;
        for (int i = 0; i < deck.size(); i++) {
            totalValue += normalDustValue(deck.get(i).getRaridade());
        }
        return totalValue;
    }

    public static double quoeficiente(List<Card> deck) {
        double tempValue = 0, totalValue = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getClasse().equals("Neutro")) {
                tempValue += neutralQuoeficient(deck.get(i).getRaridade());
            } else {
                tempValue += classQuoeficient(deck.get(i).getRaridade());
            }
            totalValue += expansionQuoeficient(tempValue, deck.get(i).getExpansao());
        }
        return totalValue;
    }

    public static double quoeficienteDeInvestimento(List<Card> deck) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        double valor = investimentoTotal(deck) / quoeficiente(deck);
        return Double.parseDouble((decimalFormat.format(valor * 1000)));
    }


    public static String classificacaoGeral(List<Card> deck) {
        double quoef = quoeficienteDeInvestimento(deck);
        String investimentoEh = "";

        if (quoef > 6 && quoef < 8) {
            investimentoEh = "Medio";
        } else if (quoef < 6) {
            if (quoef > 4 && quoef < 6) {
                investimentoEh = "Bom";
            } else if (quoef < 4) {
                if (quoef > 2 && quoef < 4) {
                    investimentoEh = "Ótimo";
                } else if (quoef < 2) {
                    investimentoEh = "Excelente";
                }
            }
        } else if (quoef > 8) {
            if (quoef > 8 && quoef < 12) {
                investimentoEh = "Razoável";
            } else if (quoef > 12) {
                if (quoef > 12 && quoef < 20) {
                    investimentoEh = "Ruim";
                } else if (quoef > 20) {
                    investimentoEh = "Péssimo";
                }
            }
        }
        return investimentoEh;
    }

    private static double normalDustValue(String raridade) {
        long valorInicial = 40;
        if (raridade != null) {
            switch (raridade) {
                case "Básico":
                    return 0L;
                case "Comum":
                    return valorInicial;
                case "Raro":
                    return valorInicial * 2.5;
                case "Épico":
                    return valorInicial * 10;
                case "Lendário":
                    return valorInicial * 40;
                default:
                    valorInicial = 0L;
            }
        } else {
            return valorInicial;
        }
        return valorInicial;
    }

    private static double neutralQuoeficient(String raridade) {
        double valor = normalDustValue(raridade);
        if (raridade != null) {
            switch (raridade) {
                case "Básico":
                    return 0L;
                case "Comum":
                    return valor;
                case "Raro":
                    return valor * 2;
                case "Épico":
                    return valor * 3;
                case "Lendário":
                    return valor * 5;
                default:
                    valor = 0L;
            }
        } else {
            return valor;
        }
        return valor;
    }

    private static double classQuoeficient(String raridade) {
        double valor = normalDustValue(raridade);
        if (raridade != null) {
            switch (raridade) {
                case "Básico":
                    return 0L;
                case "Comum":
                    return valor;
                case "Raro":
                    return valor * 1;
                case "Épico":
                    return valor * 1;
                case "Lendário":
                    return valor * 2;
                default:
                    valor = 0L;
            }
        } else {
            return valor;
        }
        return valor;
    }

    private static double expansionQuoeficient(double valor, String expansao) {
        if (expansao != null) {
            switch (expansao) {
                case "Journey to UnGoro":
                    return valor * 1;
                case "Knights of the Frozen Throne":
                    return valor * 2;
                case "Kobolds and Catacombs":
                    return valor * 3;
                case "The Witchwood":
                    return valor * 4;
                case "The Boomsday Project":
                    return valor * 5;
                case "Rastakhan is Rumble":
                    return valor * 6;
                case "Classic":
                    return valor * 12;
            }
        } else {
            return valor;
        }
        return valor;
    }

}

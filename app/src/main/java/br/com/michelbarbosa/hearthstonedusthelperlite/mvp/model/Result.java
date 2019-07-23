package br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model;

public class Result {

    private double investimentoTotal;
    private double quoeficiente;
    private double quoefDeInvestimento;
    private String investimento;

    public Result(double investimentoTotal, double quoeficiente, double quoefDeInvestimento, String investimento) {
        this.investimentoTotal = investimentoTotal;
        this.quoeficiente = quoeficiente;
        this.quoefDeInvestimento = quoefDeInvestimento;
        this.investimento = investimento;
    }

    public double getInvestimentoTotal() {
        return investimentoTotal;
    }

    public void setInvestimentoTotal(double investimentoTotal) {
        this.investimentoTotal = investimentoTotal;
    }

    public double getQuoeficiente() {
        return quoeficiente;
    }

    public void setQuoeficiente(double quoeficiente) {
        this.quoeficiente = quoeficiente;
    }

    public double getQuoefDeInvestimento() {
        return quoefDeInvestimento;
    }

    public void setQuoefDeInvestimento(double quoefDeInvestimento) {
        this.quoefDeInvestimento = quoefDeInvestimento;
    }

    public String getInvestimento() {
        return investimento;
    }

    public void setInvestimento(String investimento) {
        this.investimento = investimento;
    }
}

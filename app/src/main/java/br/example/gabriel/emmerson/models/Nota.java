package br.example.gabriel.emmerson.models;

public class Nota {

    private int id;
    private Double valor;

    public Nota(){}

    public Nota(Double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

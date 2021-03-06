package br.example.gabriel.emmerson.models;

import java.util.List;

public class Professor {

    private int id;
    private String nome;
    private String email;

    //AUXILIAR
    private List<Aluno> alunos;

    public Professor(){}

    public Professor(int id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

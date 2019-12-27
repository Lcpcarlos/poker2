package com.example.poker.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Aluno implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id =0 ;
    private  String nome;
    private  String sobreNome;
    private  String telefone;
    private  String email;


    @Ignore
    public Aluno(String nome, String sobreNome, String telefone, String email) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefone = telefone;
        this.email = email;

    }

    public Aluno(){

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) { this.sobreNome = sobreNome; }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() { return sobreNome; }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public String getNomeCompleto() {
        return nome + " " + sobreNome;
    }
}

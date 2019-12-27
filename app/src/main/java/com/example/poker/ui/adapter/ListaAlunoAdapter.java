package com.example.poker.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.poker.R;
import com.example.poker.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunoAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup ViewGroup) {
        View viewCriada = criaView(ViewGroup);
        Aluno alunoDevolvido = alunos.get(position);
        vinculaFormularioComLista(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vinculaFormularioComLista(View view, Aluno aluno) {
        TextView nomeCompleto = view.findViewById(R.id.item_aluno_nome);
        nomeCompleto.setText(aluno.getNomeCompleto());
        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        telefone.setText(aluno.getTelefone());
    }

    private View criaView(ViewGroup ViewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, ViewGroup, false);
    }

    public void atualiza(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
    }
}
package com.example.poker.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.poker.database.AgendaDatabase;
import com.example.poker.database.dao.AlunoDAO;
import com.example.poker.model.Aluno;
import com.example.poker.ui.adapter.ListaAlunoAdapter;

public class ListaAlunoView {
    private final ListaAlunoAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunoView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunoAdapter(this.context);
        dao = AgendaDatabase.getInstance(context)
                .getRoomAlunoDAO();
    }


    public void confirmaRemocao(final MenuItem item) {
        new  AlertDialog.Builder(context)
                .setTitle("Remover aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView
                                .AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                                item.getMenuInfo();
                        Aluno alunoExcolhido = adapter.getItem(menuInfo.position);
                        removeAluno(alunoExcolhido);

                    }
                })
                .setNegativeButton("náo", null)
                .show();
    }
    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
        adapter.notifyDataSetChanged();
    }
    private void removeAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
        adapter.notifyDataSetChanged();
    }
    public void configuraAdapter(ListView listaDeAlunos) {

        listaDeAlunos.setAdapter(adapter);
    }

}

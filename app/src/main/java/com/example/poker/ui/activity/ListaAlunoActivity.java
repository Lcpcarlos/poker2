package com.example.poker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poker.R;
import com.example.poker.model.Aluno;
import com.example.poker.ui.ListaAlunoView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunoActivity extends AppCompatActivity {

    private static final String TITULO_POKER = "BobPoker";
    private ListaAlunoView listaAlunosView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITULO_POKER);
        listaAlunosView = new ListaAlunoView(this);
        configuraNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_aluno_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
           listaAlunosView.confirmaRemocao(item);
        }
            return super.onContextItemSelected(item);
     }

    private void configuraNovoAluno() {
        FloatingActionButton botaoSalvarAluno = findViewById(R.id.floatingActionButton);

        botaoSalvarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormulario();
            }
        });
    }

    private void abreFormulario() {
        startActivity( new Intent(this,
                FormularioAluno.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        listaAlunosView.configuraAdapter(listaDeAlunos);
        configuraListenerPorClick(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void configuraListenerPorClick(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);

                abrirFormularioParaEditarAluno(alunoEscolhido);
            }
        });
    }

    private void abrirFormularioParaEditarAluno(Aluno alunoEscolhido) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunoActivity.this,
                FormularioAluno.class);
        vaiParaFormularioActivity.putExtra ("aluno", alunoEscolhido);
        startActivity(vaiParaFormularioActivity);
    }
}

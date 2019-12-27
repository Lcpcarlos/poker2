package com.example.poker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poker.R;
import com.example.poker.database.AgendaDatabase;
import com.example.poker.database.dao.AlunoDAO;
import com.example.poker.model.Aluno;

public class FormularioAluno extends AppCompatActivity {

    private static final String TITULO_APPEAR = "Novo Aluno";
    private EditText campoNome;
    private EditText campoSobreNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private AlunoDAO dao;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        dao = database.getRoomAlunoDAO();
        setTitle(TITULO_APPEAR);
        inicializacaoDosCampos();
//         botaSalvar();

        Intent dados = getIntent();
        if (dados.hasExtra("aluno")) {
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            assert aluno != null;
            campoNome.setText(aluno.getNome());
            campoSobreNome.setText(aluno.getSobreNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());

        } else {
            aluno = new Aluno();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizaFormulario();


        }
        return super.onOptionsItemSelected(item);
    }

//        private void botaSalvar() {
//        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
//        botaoSalvar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finalizaFormulario();
//            }
//        });
//    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoSobreNome = findViewById(R.id.activity_formulario_aluno_sobreNome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String sobreNome = campoSobreNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setSobreNome(sobreNome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);

//        return new Aluno(nome, telefone, email);
    }
}

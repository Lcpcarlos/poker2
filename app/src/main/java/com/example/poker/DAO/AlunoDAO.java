package com.example.poker.DAO;

import com.example.poker.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO  {
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;
    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if(alunoEncontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if(alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }

    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a:
             alunos) {
            if (a.getId() == aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}

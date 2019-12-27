package com.example.poker.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.poker.model.Aluno;

import java.util.List;

@Dao
public interface AlunoDAO {
    @Insert
    void salva(Aluno aluno);

    @Query("SELECT * FROM Aluno")
    List<Aluno> todos();

    @Delete
    void remove(Aluno aluno);

    @Update
    void edita(Aluno aluno);
}

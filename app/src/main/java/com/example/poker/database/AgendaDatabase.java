package com.example.poker.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.poker.database.dao.AlunoDAO;
import com.example.poker.model.Aluno;

@Database(entities = {Aluno.class}, version = 2, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String NOME_BANDO_DADOS = "poker2.db";

    public abstract AlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context) {
        return   Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_BANDO_DADOS)
                .addMigrations(new Migration(1, 2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("ALTER TABLE Aluno ADD COLUMN sobreNome TEXT");
                    }
                })
                .allowMainThreadQueries()
                .build();
    }

}

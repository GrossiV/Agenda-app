package br.edu.ibta.aluno.contacts.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.edu.ibta.aluno.contacts.modelo.Aluno;

import static br.edu.ibta.aluno.contacts.R.id.nome;

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context){
        super(context,"Agenda",null,1);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL); ";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        String sql = "DROP TABLE IF EXISTS Alunos;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(aluno);


        db.insert("Alunos",null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoAluno(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco",aluno.getEndereco());
        dados.put("nota",aluno.getNota());
        dados.put("telefone",aluno.getTelefone());
        dados.put("site",aluno.getSite());
        return dados;
    }

    public List<Aluno> buscaAlunos() {
        String sql = " SELECT * FROM Alunos; ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Aluno> alunos = new ArrayList<Aluno>();
        while (c.moveToNext()) {

          Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getReadableDatabase();


        String[] params={aluno.getId().toString()};
        db.delete("Alunos","id = ?",params);
    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues dados = pegaDadosDoAluno(aluno);

        String[] params = {aluno.getId().toString()};
        db.update("Alunos", dados, "id = ?", params  );
    }
}

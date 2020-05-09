package br.com.unipac.apptrabalhos.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import br.com.unipac.apptrabalhos.model.domain.Aluno;

public class DBHelper extends SQLiteOpenHelper {

    private static final String APP_TRABALHOS_DB = "AppTrabalhos.db";
    private static final int VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, APP_TRABALHOS_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Aluno.CREATE_TABLE_ALUNO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL(Aluno.DROP_TABLE_ALUNO);
       onCreate(db);
    }
}

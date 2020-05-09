package br.com.unipac.apptrabalhos.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.unipac.apptrabalhos.R;
import br.com.unipac.apptrabalhos.model.domain.Aluno;
import br.com.unipac.apptrabalhos.model.repository.AlunoRepository;

public class AlunoForm extends AppCompatActivity {
    EditText nameEdt, registerEdt;
    Button saveBtn;
    private AlunoRepository alunoRepository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_form);

        nameEdt = (EditText) findViewById(R.id.nameEdt);
        registerEdt = (EditText) findViewById(R.id.registerEdt);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        alunoRepository = new AlunoRepository(this);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = new Aluno();
                aluno.setName(nameEdt.getText().toString());
                aluno.setRegister(Integer.parseInt(registerEdt.getText().toString()));

                boolean save = alunoRepository.save(aluno);
                if (save) {
                    Toast.makeText(getApplicationContext(), "Dados Cadastrados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Dados Não Cadastrados", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

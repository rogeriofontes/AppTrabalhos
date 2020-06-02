package br.com.unipac.apptrabalhos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

import br.com.unipac.apptrabalhos.model.domain.Aluno;
import br.com.unipac.apptrabalhos.model.repository.AlunoRepository;
import br.com.unipac.apptrabalhos.ui.AlunoForm;
import br.com.unipac.apptrabalhos.ui.adapters.AlunoAdapter;

public class MainActivity extends AppCompatActivity {
    Button formBtn;
    ListView listaDeAlunos;
    private AlunoAdapter alunoAdaper;
    private AlunoRepository alunoRepository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaDeAlunos = (ListView) findViewById(R.id.lista_aluno);
        //formBtn = (Button) findViewById(R.id.formBtn);

        alunoRepository = new AlunoRepository(this);

        /*/formBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AlunoForm.class);
                startActivity(i);
            }
        }); */
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Aluno> alunoList = alunoRepository.getAll();
        Log.i("ALUNOS_LIST", alunoList.toString());

        if (!alunoList.isEmpty()) {
            alunoAdaper = new AlunoAdapter(alunoList, this);
            listaDeAlunos.setAdapter(alunoAdaper);
        } else {
            Toast.makeText(this, "Não existe Alunos no Banco", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idItem = item.getItemId();

        switch (idItem) {
            case  R.id.menuAdd:
                Intent i = new Intent(this, AlunoForm.class);
                startActivity(i);
                return true;
            case R.id.menuList:
                Toast.makeText(this, "Não tenho lista", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuSms:
                sendSMS("3499999999", "@rogeriofontes" + "|" + "Ola mundo SMS" );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isPermissaoParaEnviarSms() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void sendSMS(String numero, String messagem) {

        if (isPermissaoParaEnviarSms()) {
            try{
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(numero, null, messagem, null, null);
                Toast.makeText(this, "Mensagem Enviada", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                Log.e("ERROR", e.getMessage());
                e.fillInStackTrace();
            }
        } else {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10);
            }
        }

    }
}

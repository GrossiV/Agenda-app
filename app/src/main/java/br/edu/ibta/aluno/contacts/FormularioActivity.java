package br.edu.ibta.aluno.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ibta.aluno.contacts.dao.AlunoDAO;
import br.edu.ibta.aluno.contacts.modelo.Aluno;

import static android.R.id.edit;
import static br.edu.ibta.aluno.contacts.R.menu.menuformulario;

public class FormularioActivity extends AppCompatActivity {


    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
if(aluno != null){
    helper.preencheFormulario(aluno);
}


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(menuformulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                AlunoDAO dao = new AlunoDAO(this);

                Aluno aluno =  helper.pegaAluno();


                if (aluno.getId() != null) {
                    dao.altera(aluno);
                } else{

                    dao.insere(aluno);

                }


                dao.close();

                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
            return super.onOptionsItemSelected(item);
    }

}
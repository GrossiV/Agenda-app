package br.edu.ibta.aluno.contacts;

import android.widget.EditText;
import android.widget.RatingBar;

import br.edu.ibta.aluno.contacts.modelo.Aluno;


public class FormularioHelper {


    private final EditText camponome;
    private final RatingBar camponota;
    private final EditText campotelefone;
    private final EditText campoendereco;
    private final EditText camposite;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        camponome = (EditText) activity.findViewById(R.id.nome);
        campotelefone = (EditText) activity.findViewById(R.id.telefone);
        campoendereco = (EditText) activity.findViewById(R.id.endereco);
        camposite = (EditText) activity.findViewById(R.id.site);
        camponota = (RatingBar) activity.findViewById(R.id.ratingbar);
        aluno = new Aluno();
    }

    public Aluno pegaAluno() {
        aluno.setNome(camponome.getText().toString());
        aluno.setEndereco(campoendereco.getText().toString());
        aluno.setTelefone(campotelefone.getText().toString());
        aluno.setNota(Double.valueOf(camponota.getProgress()));
        aluno.setSite(camposite.getText().toString());
        return aluno;

    }

    public void preencheFormulario(Aluno aluno) {

        camponome.setText(aluno.getNome());
        campoendereco.setText(aluno.getEndereco());
        campotelefone.setText(aluno.getTelefone());
        camposite.setText(aluno.getSite());
        camponota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }

}

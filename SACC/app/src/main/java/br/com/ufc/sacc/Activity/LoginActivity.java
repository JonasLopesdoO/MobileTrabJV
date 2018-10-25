package br.com.ufc.sacc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.ufc.sacc.DAO.ConfiguracaoFirebase;
import br.com.ufc.sacc.Model.Usuario;
import br.com.ufc.sacc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogar;
    private TextView tvAbreCadastro;

    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogar = findViewById(R.id.btnLogar);
        tvAbreCadastro = findViewById(R.id.tvAbreCadastro);

        Toast.makeText(LoginActivity.this, "Digite os dados de login", Toast.LENGTH_SHORT).show();


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampos(edtEmail.getText().toString(), edtSenha.getText().toString())){

                    usuario = new Usuario();
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());
                    validarLogin();
                }
            }
        });

        tvAbreCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaCadastroUsuario();
            }
        });
    }

    private void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getAutenticacaoFirebase();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login efetuado. Bem vindo ao SACCS", Toast.LENGTH_SHORT).show();
                    abrirTelaPrincipal();
                }
            }
        });
    }

    private boolean validarCampos(String email, String senha){
        if((email == "" || email == null || email.equals("")) && (senha == "" || senha == null || senha.equals(""))){
            Toast.makeText(LoginActivity.this, "Digite o email e a senha para prosseguir", Toast.LENGTH_SHORT).show();
            return false;

        }else if(email == "" || email == null || email.equals("")){
            Toast.makeText(LoginActivity.this, "Digite o email para prosseguir", Toast.LENGTH_SHORT).show();
            return false;

        }else if(senha == "" || senha == null || senha.equals("")){
            Toast.makeText(LoginActivity.this, "Digite a senha para prosseguir", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void abrirTelaPrincipal(){
        Intent intentAbrirTelaPrincipal = new Intent(LoginActivity.this, PrincipalActivity.class);
        //intentAbrirTelaPrincipal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentAbrirTelaPrincipal);
        onBackPressed();
        //finish();
    }

    //public void onBackPressed() {
      //  this.moveTaskToBack(true);
    //}

    public void abrirTelaCadastroUsuario(){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
}

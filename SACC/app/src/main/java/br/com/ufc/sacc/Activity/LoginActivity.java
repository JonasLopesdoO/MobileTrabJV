package br.com.ufc.sacc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.ufc.sacc.DAO.ConfiguracaoFirebase;
import br.com.ufc.sacc.Model.Usuario;
import br.com.ufc.sacc.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogar;
    private TextView tvAbreCadastro;
    private LoginButton mLoginButtonFb;
    private CallbackManager mCallBackManager;

    private FirebaseAuth autenticacao;
    private FirebaseAuth mFirebaseAuth;
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

        inicializarComponenteFacebook();
        inicializarFirebaseCallBack();
        clickButtonFacebook();
    }

    private void clickButtonFacebook() {
        mLoginButtonFb.registerCallback(mCallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                firebaseLoginFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                alert("Operação cancelada");
            }

            @Override
            public void onError(FacebookException error) {
                alert("Erro ao logar com o facebook, tente novamente");
            }
        });
    }

    private void firebaseLoginFacebook(AccessToken accessToken) {
        AuthCredential credencial = FacebookAuthProvider.getCredential(accessToken.getToken());
        mFirebaseAuth.signInWithCredential(credencial).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                }else{
                    alert("Erro de autenticação com o Servidor.");
                }

            }
        });

    }

    private void inicializarFirebaseCallBack() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mCallBackManager = CallbackManager.Factory.create();
        //criar uma transposição para o Singleton depois
    }

    private void inicializarComponenteFacebook() {
        mLoginButtonFb = findViewById(R.id.btnLogarFacebook);
        mLoginButtonFb.setReadPermissions("email", "public_profile");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallBackManager.onActivityResult(requestCode, resultCode, data);
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
        startActivity(intentAbrirTelaPrincipal);
        onBackPressed();
    }

    public void abrirTelaCadastroUsuario(){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    private void alert(String operação_cancelada) {
        Toast.makeText(LoginActivity.this, operação_cancelada, Toast.LENGTH_LONG).show();
    }
}

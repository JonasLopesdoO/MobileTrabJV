package br.com.ufc.sacc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import br.com.ufc.sacc.R;

public class MainActivity extends AppCompatActivity {

    private Button btnAbrirActivityLogin;
    private Button btnAbrirActivityCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrirActivityLogin = findViewById(R.id.btnFazerLogin);
        btnAbrirActivityCadastro = findViewById(R.id.btnFazerCadastro);

        btnAbrirActivityLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirTelaLogin = new Intent(MainActivity.this, LoginActivity.class);
                //intentAbrirTelaLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentAbrirTelaLogin);
                //finish();
            }
        });

        btnAbrirActivityCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirTelaCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intentAbrirTelaCadastro);
            }
        });
    }
}

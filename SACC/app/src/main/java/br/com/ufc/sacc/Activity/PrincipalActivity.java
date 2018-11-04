package br.com.ufc.sacc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import br.com.ufc.sacc.R;

public class PrincipalActivity extends AppCompatActivity {

    Button btnLogout;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mDrawerLayout = findViewById(R.id.drawer) ;
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

<<<<<<< HEAD
<<<<<<< HEAD
        btnLogout = findViewById(R.id.btnLogout);

=======
>>>>>>> parent of b4bd729... Login com conta do Google funcionando
=======
>>>>>>> parent of b4bd729... Login com conta do Google funcionando
        //comentario
        //fechar tudo ao deslogar
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("SAIR", true);
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.perfil){
            Intent intentPerfil = new Intent(getApplicationContext(), PerfilActivity.class);
            startActivity(intentPerfil);
        } else if (id == R.id.mensagem){
            Intent intentMensagem = new Intent(getApplicationContext(), MensagemActivity.class);
            startActivity(intentMensagem);
        } else if (id == R.id.faq){
            Intent intentFaq= new Intent(getApplicationContext(), FaqActivity.class);
            startActivity(intentFaq);
        } else if (id == R.id.como_chegar){
            Intent intentComoChegar= new Intent(getApplicationContext(), ComoChegarActivity.class);
            startActivity(intentComoChegar);
        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}

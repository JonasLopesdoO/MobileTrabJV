package br.com.ufc.sacc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import br.com.ufc.sacc.R;

public class PrincipalActivity extends AppCompatActivity {

    Button btnLogout;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        btnLogout = findViewById(R.id.btnLogout);



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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

    }

    public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            navigationView = (NavigationView) findViewById(R.id.navView);
            navigationView.setNavigationItemSelectedListener(this);
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_item_perfil: {
                    Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.nav_item_consultas: {
                    Toast.makeText(this, "Consulta", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.nav_item_mensagem: {
                    Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.nav_item_faq: {
                    Toast.makeText(this, "Perguntas frequentes", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.nav_item_como_chegar: {
                    Toast.makeText(this, "Mapa", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        }
    }



    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}

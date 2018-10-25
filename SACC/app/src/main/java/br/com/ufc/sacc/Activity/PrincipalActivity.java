package br.com.ufc.sacc.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import br.com.ufc.sacc.R;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}

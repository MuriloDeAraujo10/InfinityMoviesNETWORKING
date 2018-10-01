package br.usjt.ads.desmob.clienteads18.controler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import br.usjt.ads.desmob.clienteads18.R;

public class MainActivity extends Activity {
    public static final String CHAVE = "br.usjt.ads.desmob.clienteads18.controler.chave";
    private EditText txtBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBusca = findViewById(R.id.txt_busca);
    }

    public void buscarClientes(View view) {
        Intent intent = new Intent(this, ListaClientesActivity.class);
        String texto = txtBusca.getText().toString();
        intent.putExtra(CHAVE, texto);
        startActivity(intent);
    }
}
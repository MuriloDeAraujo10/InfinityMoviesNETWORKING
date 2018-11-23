package br.usjt.ads.desmob.clienteads18.controler;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.ads.desmob.clienteads18.R;
import br.usjt.ads.desmob.clienteads18.model.Cliente;
import br.usjt.ads.desmob.clienteads18.model.ClienteDAO;

public class MainActivity extends Activity {
    public static final String CHAVE = "br.usjt.ads.desmob.clienteads18.controler.chave";
    private EditText txtBusca;
    public static final String URL = "/rest/clientes";
    public static final String HOST = "http://198.162.1.56:8080/app_poetas";
    public static final String CLIENTES = "br.usjt.ads.desmob.clienteads18.controller.clientes";
    Intent intent;

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
        if(ClienteDAO.isConnected(this)) {
            new DownloadClientes().execute(HOST + URL);
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    private class DownloadClientes extends AsyncTask<String, Void, ArrayList<Cliente>> {

        @Override
        protected ArrayList<Cliente> doInBackground(String... strings) {
            try {
                return ClienteDAO.getClientes(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<Cliente>();
        }

        protected void onPostExecute(ArrayList<Cliente> clientes){
            intent.putExtra(CLIENTES, clientes);
            startActivity(intent);
        }
    }
}
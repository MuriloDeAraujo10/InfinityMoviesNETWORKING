package br.usjt.ads.desmob.filmeads.controler;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.ads.desmob.filmeads.R;
import br.usjt.ads.desmob.filmeads.model.Filme;
import br.usjt.ads.desmob.filmeads.model.FilmeDAO;

public class MainActivity extends Activity {
    public static final String CHAVE = "br.usjt.ads.desmob.br.usjt.ads.desmob.filmeads.controler.chave";
    private EditText txtBusca;
    public static final String URL = "/rest/filmes";
    public static final String HOST = "http://198.162.1.56:8080/app_poetas";
    public static final String FILMES = "br.usjt.ads.desmob.br.usjt.ads.desmob.filmeads.controller.filmes";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBusca = findViewById(R.id.txt_busca);
    }

    public void buscarFilmes(View view) {
        Intent intent = new Intent(this, ListaFilmesActivity.class);
        String texto = txtBusca.getText().toString();
        intent.putExtra(CHAVE, texto);
        if(FilmeDAO.isConnected(this)) {
            new DownloadFilmes().execute(HOST + URL);
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    private class DownloadFilmes extends AsyncTask<String, Void, ArrayList<Filme>> {

        @Override
        protected ArrayList<Filme> doInBackground(String... strings) {
            try {
                return FilmeDAO.getFilmes(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<Filme>();
        }

        protected void onPostExecute(ArrayList<Filme> filmes){
            intent.putExtra(FILMES, filmes);
            startActivity(intent);
        }
    }
}
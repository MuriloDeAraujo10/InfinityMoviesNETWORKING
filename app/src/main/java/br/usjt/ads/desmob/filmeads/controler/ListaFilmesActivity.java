package br.usjt.ads.desmob.filmeads.controler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.ads.desmob.filmeads.R;
import br.usjt.ads.desmob.filmeads.model.Filme;

public class ListaFilmesActivity extends Activity {
    public static final String FILME = "br.usjt.ads.desmob.br.usjt.ads.desmob.filmeads.controllernomedofilme";
    private ArrayList<Filme> filmes, base;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_lista_filmes);
        Intent intent = getIntent();
        String chave = intent.getStringExtra(MainActivity.CHAVE);
        filmes = buscaFilmes(chave);
        ListView listView = findViewById(R.id.lista_filmes);
        FilmeAdapter adapter = new FilmeAdapter(filmes, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Filme filme = filmes.get(i);
                Intent intent = new Intent(activity, DetalheFilmeActivity.class);
                intent.putExtra(FILME, filme);
                startActivity(intent);
            }
        });

    }
    private ArrayList<Filme> buscaFilmes(String chave){
        ArrayList<Filme> resultado;

        if(chave != null && chave.length() > 0){
            resultado = new ArrayList<>();
            ArrayList<Filme> lista = getBase();
            for(Filme filme:lista){
                if(filme.getNome().toUpperCase().contains(chave.toUpperCase())){
                    resultado.add(filme);
                }
            }
            return resultado;
        } else {
            return getBase();
        }
    }
    private ArrayList<Filme> getBase() {return this.base;}
}
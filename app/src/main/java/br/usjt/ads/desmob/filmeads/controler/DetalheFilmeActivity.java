package br.usjt.ads.desmob.filmeads.controler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import br.usjt.ads.desmob.filmeads.R;
import br.usjt.ads.desmob.filmeads.model.Filme;
import br.usjt.ads.desmob.filmeads.model.FilmeDAO;
import br.usjt.ads.desmob.filmeads.model.Util;

public class DetalheFilmeActivity extends Activity {
    public static final String IMG = MainActivity.HOST+"/img/";
    private TextView nome, diretor, elenco, lancamento, descricao, popularidade, duracao;
    private ImageView foto;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);

        nome = findViewById(R.id.txt_nome);
        diretor = findViewById(R.id.detalhe_txt_diretor);
        lancamento = findViewById(R.id.detalhe_txt_lancamento);
        descricao = findViewById(R.id.detalhe_txt_descricao);
        elenco = findViewById(R.id.detalhe_txt_elenco);
        popularidade = findViewById(R.id.detalhe_txt_popularidade);
        duracao = findViewById(R.id.detalhe_txt_duracao);
        foto = findViewById(R.id.detalhe_foto_filme);
        context = this;

        Intent intent = getIntent();
        Filme filme = (Filme)intent.getSerializableExtra(ListaFilmesActivity.FILME);

        nome.setText(filme.getNome().toString());
        diretor.setText(filme.getDiretor().toString());
        elenco.setText(filme.getElenco().toString());
        lancamento.setText(filme.getLancamento().toString());
        descricao.setText(filme.getDescricao().toString());
        popularidade.setText(filme.getPopularidade());
        duracao.setText(filme.getDuracao());
        foto.setImageDrawable(Util.getDrawable(this, filme.getFigura()));
        new DownloadImagem().execute(IMG+filme.getFigura()+".jpg");
    }
    private class DownloadImagem extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap imagem = null;
            try {
                imagem = FilmeDAO.getImage(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(imagem == null){
                imagem = ((BitmapDrawable)context.getDrawable(R.drawable.emoji)).getBitmap();
            }
            return imagem;
        }

        protected void onPostExecute(Bitmap imagem){
            foto.setImageBitmap(imagem);
        }
    }
}
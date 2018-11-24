package br.usjt.ads.desmob.filmeads.model;

/*import android.app.DownloadManager;*/
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FilmeDAO {

    private static OkHttpClient client = new OkHttpClient();

    private FilmeDAO(){

    }

    public static ArrayList<Filme> getFilmes(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String arquivo = response.body().string();

        ArrayList<Filme> filmes = new ArrayList<>();

        try {
            JSONArray lista = new JSONArray(arquivo);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                Filme filme = new Filme();
                filme.setId(item.getInt("id"));
                filme.setNome(item.getString("nome"));
                filme.setEmail(item.getString("email"));
                filme.setDiretor(item.getString("diretor"));
                filme.setElenco(item.getString("elenco"));
                filme.setLancamento(item.getString("lancamento"));
                filme.setDescricao(item.getString("descricao"));
                filme.setPopularidade(item.getString("popularidade"));
                filme.setDuracao(item.getString("duracao"));
                filmes.add(filme);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return filmes;
    }


    public static Bitmap getImage(String url) throws IOException {

        Bitmap img = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
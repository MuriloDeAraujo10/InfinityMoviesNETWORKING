package br.usjt.ads.desmob.clienteads18.model;

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

public class ClienteDAO {

    private static OkHttpClient client = new OkHttpClient();

    private ClienteDAO(){

    }

    public static ArrayList<Cliente> getClientes(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String arquivo = response.body().string();

        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            JSONArray lista = new JSONArray(arquivo);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                Cliente cliente = new Cliente();
                cliente.setId(item.getInt("id"));
                cliente.setNome(item.getString("nome"));
                cliente.setEmail(item.getString("email"));
                cliente.setDiretor(item.getString("diretor"));
                cliente.setElenco(item.getString("elenco"));
                cliente.setLancamento(item.getString("lancamento"));
                cliente.setDescricao(item.getString("descricao"));
                cliente.setPopularidade(item.getString("popularidade"));
                cliente.setDuracao(item.getString("duracao"));
                clientes.add(cliente);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return clientes;
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
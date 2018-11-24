package br.usjt.ads.desmob.filmeads.controler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import br.usjt.ads.desmob.filmeads.R;
import br.usjt.ads.desmob.filmeads.model.Filme;
import br.usjt.ads.desmob.filmeads.model.Util;

public class FilmeAdapter extends BaseAdapter {
    private ArrayList<Filme> filmes;
    private Context context;

    public FilmeAdapter(ArrayList<Filme> filmes, Context context) {

        this.filmes = filmes;
        this.context = context;
    }
    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public Object getItem(int i) {
        return filmes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View linha = view;
                   if (linha == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            linha = inflater.inflate(R.layout.linha_filme, viewGroup, false);
            ImageView fotoFilme = linha.findViewById(R.id.foto_cliente);
            /*ImageView fotoFilme = linha.findViewById(R.id.foto_filme);*/
            TextView linhaNome = linha.findViewById(R.id.linha_nome);
            TextView linhaDetalhe = linha.findViewById(R.id.linha_detalhe);
            ViewHolder holder = new ViewHolder(fotoFilme, linhaNome, linhaDetalhe);
            linha.setTag(holder);
        }

        Filme filme = filmes.get(i);

        ViewHolder holder = (ViewHolder) linha.getTag();

        holder.getLinha1().setText(filme.getNome() +  " - ID: " + filme.getId());
        holder.getLinha2().setText("Diretor:" + (filme.getDiretor() + " - Lançamento:" + filme.getLancamento()));

        Drawable drawable = Util.getDrawable(context, filme.getFigura());
        holder.getImagem().setImageDrawable(drawable);
        return linha;
    }
}
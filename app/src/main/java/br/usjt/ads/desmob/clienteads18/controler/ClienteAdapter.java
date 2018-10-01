package br.usjt.ads.desmob.clienteads18.controler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import br.usjt.ads.desmob.clienteads18.R;
import br.usjt.ads.desmob.clienteads18.model.Cliente;
import br.usjt.ads.desmob.clienteads18.model.Util;

public class ClienteAdapter extends BaseAdapter {
    private ArrayList<Cliente> clientes;
    private Context context;

    public ClienteAdapter(ArrayList<Cliente> clientes, Context context) {

        this.clientes = clientes;
        this.context = context;
    }
    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int i) {
        return clientes.get(i);
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
            linha = inflater.inflate(R.layout.linha_cliente, viewGroup, false);
            ImageView fotoCliente = linha.findViewById(R.id.foto_cliente);
            TextView linhaNome = linha.findViewById(R.id.linha_nome);
            TextView linhaDetalhe = linha.findViewById(R.id.linha_detalhe);
            ViewHolder holder = new ViewHolder(fotoCliente, linhaNome, linhaDetalhe);
            linha.setTag(holder);
        }

        Cliente cliente = clientes.get(i);

        ViewHolder holder = (ViewHolder) linha.getTag();

        holder.getLinha1().setText(cliente.getNome() +  " - ID: " + cliente.getId());
        holder.getLinha2().setText("Diretor:" + (cliente.getDiretor() + " - Lançamento:" + cliente.getLancamento()));

        Drawable drawable = Util.getDrawable(context, cliente.getFigura());
        holder.getImagem().setImageDrawable(drawable);
        return linha;
    }
}
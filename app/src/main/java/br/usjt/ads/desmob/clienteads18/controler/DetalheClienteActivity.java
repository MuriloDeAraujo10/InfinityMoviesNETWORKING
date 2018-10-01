package br.usjt.ads.desmob.clienteads18.controler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import br.usjt.ads.desmob.clienteads18.R;
import br.usjt.ads.desmob.clienteads18.model.Util;
import br.usjt.ads.desmob.clienteads18.model.Cliente;

public class DetalheClienteActivity extends Activity {
    private TextView nome, diretor, elenco, lancamento, descricao, popularidade, duracao;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cliente);

        nome = findViewById(R.id.txt_nome);
        diretor = findViewById(R.id.detalhe_txt_diretor);
        lancamento = findViewById(R.id.detalhe_txt_lancamento);
        descricao = findViewById(R.id.detalhe_txt_descricao);
        elenco = findViewById(R.id.detalhe_txt_elenco);
        popularidade = findViewById(R.id.detalhe_txt_popularidade);
        duracao = findViewById(R.id.detalhe_txt_duracao);
        foto = findViewById(R.id.detalhe_foto_cliente);

        Intent intent = getIntent();
        Cliente cliente = (Cliente)intent.getSerializableExtra(ListaClientesActivity.CLIENTE);

        nome.setText(cliente.getNome().toString());
        diretor.setText(cliente.getDiretor().toString());
        elenco.setText(cliente.getElenco().toString());
        lancamento.setText(cliente.getLancamento().toString());
        descricao.setText(cliente.getDescricao().toString());
        popularidade.setText(cliente.getPopularidade());
        duracao.setText(cliente.getDuracao());
        foto.setImageDrawable(Util.getDrawable(this, cliente.getFigura()));
    }
}
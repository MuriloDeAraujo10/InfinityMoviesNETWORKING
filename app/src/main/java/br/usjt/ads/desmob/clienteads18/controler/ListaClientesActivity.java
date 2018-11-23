package br.usjt.ads.desmob.clienteads18.controler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.ads.desmob.clienteads18.R;
import br.usjt.ads.desmob.clienteads18.model.Cliente;

public class ListaClientesActivity extends Activity {
    public static final String CLIENTE = "br.usjt.ads.desmob.clienteads18.controllernomedocliente";
    private ArrayList<Cliente> clientes, base;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_lista_clientes);
        Intent intent = getIntent();
        String chave = intent.getStringExtra(MainActivity.CHAVE);
        clientes = buscaClientes(chave);
        ListView listView = findViewById(R.id.lista_clientes);
        ClienteAdapter adapter = new ClienteAdapter(clientes, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cliente cliente = clientes.get(i);
                Intent intent = new Intent(activity, DetalheClienteActivity.class);
                intent.putExtra(CLIENTE, cliente);
                startActivity(intent);
            }
        });

    }
    private ArrayList<Cliente> buscaClientes(String chave){
        ArrayList<Cliente> resultado;

        if(chave != null && chave.length() > 0){
            resultado = new ArrayList<>();
            ArrayList<Cliente> lista = getBase();
            for(Cliente cliente:lista){
                if(cliente.getNome().toUpperCase().contains(chave.toUpperCase())){
                    resultado.add(cliente);
                }
            }
            return resultado;
        } else {
            return getBase();
        }
    }
    private ArrayList<Cliente> getBase() {return this.base;}
}
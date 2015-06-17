package com.example.api.ejemplolistview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    EditText etArticulo;
    GridView lvLista;
    String[] valores = new String[]{"Samsung", "Huawei", "iPhone", "Nexus",
            "M5"};
    ArrayAdapter adaptador;
    ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvLista = (GridView) findViewById(R.id.list);
        etArticulo = (EditText) findViewById(R.id.editText);
        lista = new ArrayList<String>();
        for (int i = 0; i < valores.length; i++) {
            lista.add(valores[i]);
        }
        adaptador = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, valores);

        lvLista.setAdapter(adaptador);
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0).withEndAction(
                        new Runnable() {
                            @Override
                            public void run() {
                                lista.remove(item);
                                adaptador.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            };
        });
    }

    public void mostrarListView(View view){
        Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
        startActivity(intent);

    }

    public void agregarNombre(View view) {
        String sArticulo;
        sArticulo = etArticulo.getText().toString().trim();
        lista.add(sArticulo);
        adaptador.notifyDataSetChanged();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

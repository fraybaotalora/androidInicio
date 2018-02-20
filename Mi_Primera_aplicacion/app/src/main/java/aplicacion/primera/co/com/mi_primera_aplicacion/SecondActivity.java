package aplicacion.primera.co.com.mi_primera_aplicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView nombre;
    EditText usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nombre= (TextView) findViewById(R.id.id);
        usuario= (EditText) findViewById(R.id.context);

        Intent intent= getIntent();
        Bundle extras= intent.getExtras();


        if(!extras.isEmpty()) {
            String nomb = extras.getString("id");
            String user = extras.getString("context");
            nombre.setText(nomb);
            usuario.setText(user);
        }
        else{
            Toast.makeText(getApplicationContext(),"No se encontraron datos de usuario", Toast.LENGTH_SHORT);
        }


    }
}

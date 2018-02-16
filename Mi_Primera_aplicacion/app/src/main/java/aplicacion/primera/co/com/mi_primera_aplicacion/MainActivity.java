package aplicacion.primera.co.com.mi_primera_aplicacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText nombre;
    EditText usuario;
    TextView respuesta;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre= (EditText) findViewById(R.id.nombre);
        usuario= (EditText) findViewById(R.id.usuario);
        respuesta = (TextView) findViewById(R.id.respuesta);
        ingresar= (Button) findViewById(R.id.ingresar);

        ingresar.setOnClickListener(this);


        /*
        *
        * realizando cambios en el codifgo y la mimanrn
        * */
    }


    @Override
    public void onClick(View view) {

        String nombreB= nombre.getText().toString();
        String usuarioB = usuario.getText().toString();


        respuesta.setText("Se cargaron los datoa: nombre="+nombreB +"--- usuario="+ usuarioB);


    }





}

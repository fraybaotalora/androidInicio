package aplicacion.primera.co.com.mi_primera_aplicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import aplicacion.primera.co.com.mi_primera_aplicacion.Response.GreetingResponse;
import aplicacion.primera.co.com.mi_primera_aplicacion.service.UsuarioService;
import aplicacion.primera.co.com.mi_primera_aplicacion.util.Preferencia;
import aplicacion.primera.co.com.mi_primera_aplicacion.util.RetrofitAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private UsuarioService usuarioService;
    private Preferencia preferencia;
    private String nombreB;
    private String contrasenaB;

    EditText nombre;
    EditText usuario;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferencia= new Preferencia();
        usuarioService= RetrofitAdapter.getRetrofit().create(UsuarioService.class);
        nombreB= preferencia.leerValor(this,"user");

        if(nombreB.isEmpty()) {

             nombre = (EditText) findViewById(R.id.nombre);
             usuario = (EditText) findViewById(R.id.usuario);
             ingresar = (Button) findViewById(R.id.ingresar);

             ingresar.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     nombreB = nombre.getText().toString();
                     contrasenaB = usuario.getText().toString();

                     if (nombreB.isEmpty() || contrasenaB.isEmpty()) {
                         Toast.makeText(getApplicationContext(), "El nombre y el usuario son obligatorios", Toast.LENGTH_SHORT).show();
                     } else {

                         Log.i("DATOS DE INTERFAZ", "onClick: Nombre: " + nombreB + " -- Usuario: " + contrasenaB);
                         obtenerSaludo();
                     }

                 }
             });
         }
         else{

             loginAutomatico();
         }
    }


    //consumiendo servicio
    public void obtenerSaludo(){

        Call<GreetingResponse> greeting= usuarioService.saludo();

        greeting.enqueue(new Callback<GreetingResponse>() {
            @Override
            public void onResponse(Call<GreetingResponse> call, Response<GreetingResponse> response) {
                if(response.isSuccessful()){

                    Log.i("obtenerSaludo", "URI: " + call.request().url());
                    Log.i("obtenerSaludo", "Response: " + response.body());

                    GreetingResponse s= response.body();
                    preferencia.guardarValor(MainActivity.this, "user",s.getId());
                    preferencia.guardarValor(MainActivity.this, "contrasena",s.getContent());

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("id", s.getId());
                    intent.putExtra("context", s.getContent());
                    startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this,"Error obteniendo los datos del servicio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GreetingResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error al consumir el servicio de saludo", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void loginAutomatico(){

        Log.i("Login automatico", "Datos: " + nombreB);
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("id", nombreB);
        intent.putExtra("context",preferencia.leerValor(this,"contrasena"));
        startActivity(intent);


    }




}










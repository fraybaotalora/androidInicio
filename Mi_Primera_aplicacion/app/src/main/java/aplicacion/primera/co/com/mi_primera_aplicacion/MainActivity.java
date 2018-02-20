package aplicacion.primera.co.com.mi_primera_aplicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import aplicacion.primera.co.com.mi_primera_aplicacion.DTO.Saludo;
import aplicacion.primera.co.com.mi_primera_aplicacion.Response.GreetingResponse;
import aplicacion.primera.co.com.mi_primera_aplicacion.service.UsuarioService;
import aplicacion.primera.co.com.mi_primera_aplicacion.util.RetrofitAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private UsuarioService usuarioService;
    EditText nombre;
    EditText usuario;

    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioService= RetrofitAdapter.getRetrofit().create(UsuarioService.class);


        nombre = (EditText) findViewById(R.id.nombre);
        usuario = (EditText) findViewById(R.id.usuario);
        ingresar = (Button) findViewById(R.id.ingresar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreB = nombre.getText().toString();
                String usuarioB = usuario.getText().toString();

                if (nombreB.isEmpty() || usuarioB.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El nombre y el usuario son obligatorios", Toast.LENGTH_SHORT).show();
                } else {

                    Log.i("DATOS DE INTERFAZ", "onClick: Nombre: "+nombreB+" -- Usuario: "+usuarioB);
                    obtenerSaludo();


                }

            }
        });

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


}










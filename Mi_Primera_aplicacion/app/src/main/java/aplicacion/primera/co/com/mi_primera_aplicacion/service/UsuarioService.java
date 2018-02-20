package aplicacion.primera.co.com.mi_primera_aplicacion.service;

import aplicacion.primera.co.com.mi_primera_aplicacion.Response.GreetingResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by frayotto on 20/02/2018.
 */

public interface UsuarioService {

    @GET("greeting")
    Call<GreetingResponse> saludo();




}

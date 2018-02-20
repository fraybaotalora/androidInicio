package aplicacion.primera.co.com.mi_primera_aplicacion.Response;

/**
 * Created by frayotto on 20/02/2018.
 */

public class GreetingResponse {

    private String id;
    private String content;

    public GreetingResponse(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

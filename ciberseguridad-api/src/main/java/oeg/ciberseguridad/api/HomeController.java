package oeg.ciberseguridad.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore 
@RestController
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "<html>Indicadores de ciberseguridad. <a href=\"swagger-ui.html\">Documentación</a> de la API. See also the <a href=\"http://ciberseg.linkeddata.es/def/ontologiaciberseguridad/index-es.html#\">ontology</a></html>";
    }
}
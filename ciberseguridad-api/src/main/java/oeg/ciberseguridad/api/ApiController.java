package oeg.ciberseguridad.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vroddon
 */
@Api(value = "Indicadores de ciberseguridad", description = "Servicio para calcular dinámicamente algunos indicadores del sector de ciberseguridad", tags="indicadores")
@RestController
@RequestMapping("/api")
public class ApiController {
    @ApiOperation(value = "Obtiene indicadores de ciberseguridad basados en patentes", notes = "Not documented.", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),@ApiResponse(code = 403, message = "Not authorized"),@ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/patentes", method = RequestMethod.GET)
    public String patentes(@RequestParam(value = "year", defaultValue = "") String param) {
        return "Victor Rodriguez Doncel - Ontology Engineering Group";
    }
    
    @ApiOperation(value = "Internal operations", notes = "Not documented.", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),@ApiResponse(code = 403, message = "Not authorized"),@ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String internal(@ApiParam(value = "Joker parameter", required = false, defaultValue = "valor") @RequestParam(value = "param", defaultValue = "") String param) {
        return "Victor Rodriguez Doncel - Ontology Engineering Group";
    }
   
}

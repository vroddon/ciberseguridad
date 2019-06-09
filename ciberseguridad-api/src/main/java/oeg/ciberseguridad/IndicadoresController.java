package oeg.ciberseguridad;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vroddon
 */
@RestController
@RequestMapping("/indicadores")
@Api(value = "Indicadores de ciberseguridad", description = "Servicio para calcular dinámicamente algunos indicadores del sector de ciberseguridad")
public class IndicadoresController {
    
    @ApiOperation(value = "Patentes", notes = "Devuelve el número de patentes cuya empresa está domiciliada en dicho país", response = String.class, tags = "annotation")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok")
        ,
    @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/patentes", method = RequestMethod.POST, produces = "application/json")
    public String people(
            @ApiParam(value = "palabraclave", required = false) @RequestParam("keyword") String keyword,
            @ApiParam(value = "formato", required = false, defaultValue = "json", allowableValues = "txt, json", allowMultiple = false) @RequestParam("formato") String formato
    ) throws Exception {
        return "si";
    }    
    
    @ApiOperation(value = "Internal operations", notes = "Not documented.", response = String.class, tags = "internal")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success")
        ,
    @ApiResponse(code = 403, message = "Not authorized")
        ,
    @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String internal(@ApiParam(value = "Joker parameter", required = false, defaultValue = "valor") @RequestParam("param") String param) {
        return "Victor Rodriguez Doncel - Ontology Engineering Group";
    }
    
    
}

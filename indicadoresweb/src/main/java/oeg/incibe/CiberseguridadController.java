package oeg.incibe;

import org.springframework.web.bind.annotation.*;
/**
 *
 * @author vroddon
 */
@RestController
public class CiberseguridadController {
    @GetMapping(path = "/test9")
    public String get() {
        return "hello world";
    }
}
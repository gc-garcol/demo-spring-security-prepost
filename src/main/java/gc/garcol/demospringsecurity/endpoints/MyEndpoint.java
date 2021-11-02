package gc.garcol.demospringsecurity.endpoints;

import gc.garcol.demospringsecurity.aop.LogParam;
import gc.garcol.demospringsecurity.dto.Hello;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author garcol
 */
@RestController
@RequestMapping("/api")
public class MyEndpoint {

    @GetMapping("/test/{param}")
    @PreAuthorize("@MySecurityService.hasTrueParams(#param)")
    public String hello(@PathVariable("param") String param) {
        return "checked: " + param;
    }

    @PostMapping("/test")
    @LogParam
    @PreAuthorize("@MySecurityService.hasTrueParams(#hello.username)")
    public Hello postHello(@RequestBody Hello hello) {
        return hello;
    }



}

package gc.garcol.demospringsecurity.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

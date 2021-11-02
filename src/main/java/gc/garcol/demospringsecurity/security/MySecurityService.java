package gc.garcol.demospringsecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author garcol
 */
@Slf4j
@Service(value = "MySecurityService")
public class MySecurityService {

    private final static Set<String> permitedParams = new HashSet<>();

    static {
        permitedParams.add("thai");
        permitedParams.add("mei");
    }

    public boolean hasTrueParams(String param) {
        log.info("check valid param: {}", param);
        return permitedParams.contains(param);
    }

}

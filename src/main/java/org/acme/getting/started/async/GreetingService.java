package org.acme.getting.started.async;

import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class GreetingService {

    Logger logger = LoggerFactory.getLogger("GreetingService");

    public String foo() {
        logger.info("foo -> bar");
        return "bar";
    }

}

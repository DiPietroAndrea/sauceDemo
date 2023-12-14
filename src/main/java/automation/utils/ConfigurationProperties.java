package automation.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("framework.properties")
public class ConfigurationProperties {

    @Value("${browser}")
    private String browser;

    @Value(("${titolohomepage}"))
    private String titoloHomePage;

    public String getTitoloHomePage() {
        return titoloHomePage;
    }
    public String getBrowser() {
        return browser;
    }
}

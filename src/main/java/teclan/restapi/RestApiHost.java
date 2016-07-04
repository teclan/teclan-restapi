package teclan.restapi;

import org.restlet.Context;

import com.google.inject.Inject;

public class RestApiHost {
    private RestApiComponent component;

    @Inject
    public RestApiHost(RestApiComponent component) {
        this.component = component;
    }

    public void start() throws Exception {
        component.start();

        Context.getCurrentLogger().info(
                "Restlet application started. URL: http://127.0.0.1:6021/v1");
    }

    public void stop() throws Exception {
        component.stop();
    }

}

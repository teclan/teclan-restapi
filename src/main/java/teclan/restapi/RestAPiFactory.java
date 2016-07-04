package teclan.restapi;

import org.restlet.routing.Router;

public class RestAPiFactory {

    private static RestApiHost restApiHost = null;

    public static RestApiHost getInstance() {

        if (restApiHost == null) {
            restApiHost = new RestApiHost(new RestApiComponent("127.0.0.1",
                    6021, new RestApiApplication(new RestApiRouter() {

                        public void createRoutes(Router router) {
                            // TODO Auto-generated method stub

                        }
                    })));
        }

        return restApiHost;

    }

}

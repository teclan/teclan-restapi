package teclan.restapi;

import org.restlet.Restlet;
import org.restlet.ext.guice.ResourceInjectingApplication;
import org.restlet.routing.Router;

import com.google.inject.Inject;

public class RestApiApplication extends ResourceInjectingApplication {
    public static final String ROUTE_EVENT_CREATE  = "/logs";
    public static final String ROUTE_EVENT_DESTROY = "/destroy";
    public static final String ROUTE_EVENT_FETCH   = "/fetch/{id}";

    private RestApiRouter router;

    @Inject
    public RestApiApplication(RestApiRouter router) {
        setName("Pixiu gui" + " REST API");

        this.router = router;
    }

    @Override
    public Restlet createInboundRoot() {
        return createApiRouter();
    }

    private Router createApiRouter() {

        Router apiRouter = new Router(getContext());
        apiRouter.setFinderClass(null);

        apiRouter.attach(ROUTE_EVENT_CREATE, LogsResource.class);
        apiRouter.attach(ROUTE_EVENT_DESTROY, LogsResource.class);
        apiRouter.attach(ROUTE_EVENT_FETCH, FetchResources.class);

        router.createRoutes(apiRouter);

        return apiRouter;

    }
}

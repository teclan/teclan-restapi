package teclan.restapi;

import org.restlet.Component;
import org.restlet.data.Protocol;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class RestApiComponent extends Component {

    public String address;

    public int port;

    @Inject
    public RestApiComponent(@Named("config.webapi.bind-address") String address,
            @Named("config.webapi.bind-port") int port,
            RestApiApplication application) {
        getServers().add(Protocol.HTTP, address, port);
        getDefaultHost().attach("/v1", application);

        this.address = address;
        this.port = port;
    }
}

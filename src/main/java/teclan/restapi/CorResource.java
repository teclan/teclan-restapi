package teclan.restapi;

import java.util.concurrent.ConcurrentMap;

import org.restlet.Message;
import org.restlet.engine.header.Header;
import org.restlet.ext.guice.SelfInjectingServerResource;
import org.restlet.resource.Options;
import org.restlet.util.Series;

public class CorResource extends SelfInjectingServerResource {

    private static final String HEADERS_KEY = "org.restlet.http.headers";

    @SuppressWarnings("unchecked")
    protected static Series<Header> getMessageHeaders(Message message) {
        ConcurrentMap<String, Object> attrs = message.getAttributes();
        Series<Header> headers = (Series<Header>) attrs.get(HEADERS_KEY);
        if (headers == null) {
            headers = new Series<Header>(Header.class);
            Series<Header> prev = (Series<Header>) attrs
                    .putIfAbsent(HEADERS_KEY, headers);

            if (prev != null) {
                headers = prev;
            }
        }

        return headers;
    }

    @Options
    public void doOptions() {
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Origin",
                "*");
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Methods",
                "GET,POST,DELETE,OPTIONS");
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Headers",
                "Content-Type,X-Requested-With");
        getMessageHeaders(getResponse()).add("Access-Control-Allow-Credentials",
                "true");
        getMessageHeaders(getResponse()).add("Access-Control-Max-Age", "60");
    }
}

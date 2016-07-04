package teclan.restapi;

import org.restlet.ext.guice.SelfInjectingServerResourceModule;

import com.google.inject.AbstractModule;

public class RestApiModule extends AbstractModule {

    @Override
    protected void configure() {

        binder().install(new SelfInjectingServerResourceModule());
    }

}

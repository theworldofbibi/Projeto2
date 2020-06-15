package org.DuaLipa;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.DuaLipa.db.ChartsDAO;
import org.DuaLipa.resource.ChartsResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class App extends Application<Configuration>
{
    public static void main( String[] args ) {
        try {
            new App().run(args);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public String getName() {
        return "did a full 180";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        AssetsBundle assetsBundle = new AssetsBundle("/site", "/", "index.html");
        bootstrap.addBundle(assetsBundle);
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        ChartsDAO dao = new ChartsDAO();
        ChartsResource chartsResource = new ChartsResource(dao);
        environment.jersey().register(chartsResource);

        environment.jersey().setUrlPattern("/api/*");
    }
}

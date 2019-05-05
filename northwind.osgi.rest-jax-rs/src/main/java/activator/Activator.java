package activator;
import java.util.Hashtable;

import javax.servlet.Servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import northwind.controller.NorthwindController;


public class Activator implements BundleActivator {

	  private ServiceRegistration registration;

	  public void start(BundleContext context) throws Exception {
	    Hashtable props = new Hashtable();
	    props.put("osgi.http.whiteboard.servlet.pattern", "/*");
	    props.put("servlet.init.jersey.config.server.provider.packages", "northwind.controller");

	    this.registration = context.registerService(Servlet.class.getName(), new org.glassfish.jersey.servlet.ServletContainer(), props);
	  }

	  public void stop(BundleContext context) throws Exception {
	    this.registration.unregister();
	  }
}
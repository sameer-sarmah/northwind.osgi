package activator;
import java.util.Hashtable;

import javax.servlet.Servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import northwind.servlet.NorthwindServlet;


public class Activator implements BundleActivator {

	  private ServiceRegistration registration;

	  public void start(BundleContext context) throws Exception {
	    Hashtable props = new Hashtable();
	    props.put("osgi.http.whiteboard.servlet.pattern", "/api");

	    this.registration = context.registerService(Servlet.class.getName(), new NorthwindServlet(), props);
	  }

	  public void stop(BundleContext context) throws Exception {
	    this.registration.unregister();
	  }
}
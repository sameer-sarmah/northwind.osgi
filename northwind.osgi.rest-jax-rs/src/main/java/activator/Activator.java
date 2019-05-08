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
		try {
			Class.forName("org.glassfish.jersey.servlet.ServletContainer");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void stop(BundleContext context) throws Exception {
		// this.registration.unregister();
	}
}
package northwind.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import api.ICategoryProvider;
import api.ICategoryService;
import category.entity.Category;
import category.entity.Product;
import exceptions.CoreException;

@Path("/api")
public class NorthwindController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String hello() throws CoreException {
		return "hello";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/categories")
	public List<Category> getCategories() throws CoreException {
		ICategoryService categoryService;
		try {
			 categoryService = findCategoryServiceProviders();
		} catch (InvalidSyntaxException e) {
			throw new CoreException("Category cant be fetched", 500);
		}
		return categoryService.findAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/products/{name}")
	public List<Product> getProductsByCategory(@PathParam("name") String name) throws CoreException {
		ICategoryService categoryService;
		try {
			 categoryService = findCategoryServiceProviders();
		} catch (InvalidSyntaxException e) {
			throw new CoreException("Category cant be fetched", 500);
		}
		return categoryService.findAllProductsByCategory(name);
	}
	
	private ICategoryService findCategoryServiceProviders() throws InvalidSyntaxException {
		List<ICategoryService> categoryServiceProvider = new ArrayList<>();
		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ServiceReference<?>[] references= bundleContext.getAllServiceReferences(ICategoryProvider.class.getName(), null);
		for(ServiceReference reference : references) {
			ICategoryService provider=(ICategoryService)bundleContext.getService(reference);
			categoryServiceProvider.add(provider);
		}
		if(!categoryServiceProvider.isEmpty()) {
			return categoryServiceProvider.get(0);
		}
		else {
			return null;
		}
	}

}

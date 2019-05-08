package northwind.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyWriter;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.google.gson.Gson;

import api.ICategoryProvider;
import api.ICategoryService;
import api.Source;
import category.entity.Category;
import category.entity.Product;
import exceptions.CoreException;

@Path("/rest")
public class NorthwindController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Category hello() throws CoreException {
		Category beverage=new Category("1", "Beverages", "Soft drinks, coffees, teas, beers, and ales");
		try {
			findMessageBodyWriterProviders();
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return beverage;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/categories")
	public String getCategories() throws CoreException {
		ICategoryService categoryService;
		try {
			 categoryService = findCategoryServiceProviders();
		} catch (InvalidSyntaxException e) {
			throw new CoreException("Category cant be fetched", 500);
		}
		Gson gson = new Gson();
		return gson.toJson(categoryService.findAll());
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/products/{name}")
	public String getProductsByCategory(@PathParam("name") String name) throws CoreException {
		ICategoryService categoryService;
		try {
			 categoryService = findCategoryServiceProviders();
		} catch (InvalidSyntaxException e) {
			throw new CoreException("Category cant be fetched", 500);
		}
		;
		Gson gson = new Gson();
		return gson.toJson(categoryService.findAllProductsByCategory(name));
	}
	
	private ICategoryService findCategoryServiceProviders() throws InvalidSyntaxException {
		List<ICategoryService> categoryServiceProvider = new ArrayList<>();
		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ServiceReference<?>[] references = bundleContext.getAllServiceReferences(ICategoryService.class.getName(),
				null);
		for (ServiceReference reference : references) {
			ICategoryService provider = (ICategoryService) bundleContext.getService(reference);
			categoryServiceProvider.add(provider);
		}
		if (!categoryServiceProvider.isEmpty()) {
			 for(ICategoryService service :categoryServiceProvider ) {
				 if(service.getSource().equals(Source.DB)) {
					 return service;
				 }
			 }
		} 
		return null;
	}
	/*
	 * org.codehaus.jackson.jaxrs.JacksonJsonProvider is not getting picked 
	 * MessageBodyWriter not found for media type=application/json, type=class category.entity.Category, genericType=class category.entity.Category.
	 * */
	private void findMessageBodyWriterProviders() throws InvalidSyntaxException{
		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ServiceReference<?>[] references = bundleContext.getAllServiceReferences(MessageBodyWriter.class.getName(),
				null);
		for (ServiceReference reference : references) {
			MessageBodyWriter provider = (MessageBodyWriter) bundleContext.getService(reference);
			System.out.println(provider.getClass().getCanonicalName());
		}
	}
	

}

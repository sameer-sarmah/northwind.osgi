package northwind.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;

import com.google.gson.Gson;


import api.ICategoryProvider;
import api.ICategoryService;
import category.entity.Category;
import category.entity.Product;
import exceptions.CoreException;

@Component(service = Servlet.class, property = { "osgi.http.whiteboard.servlet.pattern=api/*" })
//@WebServlet(urlPatterns = { "/api/*" }, loadOnStartup = 1)
public class NorthwindServlet extends HttpServlet {

	public String hello() throws CoreException {
		return "hello";
	}

	public List<Category> getCategories() throws CoreException {
		ICategoryService categoryService;
		try {
			categoryService = findCategoryServiceProviders();
		} catch (InvalidSyntaxException e) {
			throw new CoreException("Category cant be fetched", 500);
		}
		return categoryService.findAll();
	}

	public List<Product> getProductsByCategory(String name) throws CoreException {
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
		ServiceReference<?>[] references = bundleContext.getAllServiceReferences(ICategoryService.class.getName(),
				null);
		for (ServiceReference reference : references) {
			ICategoryService provider = (ICategoryService) bundleContext.getService(reference);
			categoryServiceProvider.add(provider);
		}
		if (!categoryServiceProvider.isEmpty()) {
			return categoryServiceProvider.get(0);
		} else {
			return null;
		}
	}

	private String facade(HttpServletRequest request) throws CoreException {
		String pathinfo = request.getPathInfo();
		if (pathinfo != null && !pathinfo.isEmpty()) {
			String[] params = pathinfo.split("/");
			if (params.length == 2) {
				if ("hello".equalsIgnoreCase(params[1])) {
					return hello();
				} else if ("categories".equalsIgnoreCase(params[1])) {
					Gson gson = new Gson();
					return gson.toJson(this.getCategories());
				}
			} else if (params.length == 3) {
				if ("product".equalsIgnoreCase(params[1])) {
					String name = params[2];
					Gson gson = new Gson();
					return gson.toJson(this.getProductsByCategory(name));
				}
			}
		}
		throw new CoreException("Not implemented", 500);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String json = facade(request);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);
		} catch (CoreException e) {
			throw new ServletException(e.getMessage());
		}
	}

}

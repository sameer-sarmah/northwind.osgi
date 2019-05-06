package category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import api.ICategoryProvider;
import api.ICategoryService;
import api.Source;
import category.entity.Category;
import category.entity.Product;
import exceptions.CoreException;


public class DefaultCategoryService implements ICategoryService {


	public List<Category> findAll() throws CoreException{
		List<ICategoryProvider> categoriesProvider;
		try {
			categoriesProvider = findCategoryProviders();
		} catch (InvalidSyntaxException e) {
			throw new CoreException("Category cant be fetched", 500);
		}
		List<Category> categories = categoriesProvider.stream().map((provider) -> {
			return provider.getCategory();
		}).collect(Collectors.toList());
		return categories;
	}

	@Override
	public List<Product> findAllProductsByCategory(String categoryName) throws CoreException {
		List<ICategoryProvider> categoriesProvider;
		try {
			categoriesProvider = findCategoryProviders();
		} catch (InvalidSyntaxException e) {
			throw new CoreException("Category cant be fetched", 500);
		}
		List<ICategoryProvider> categories = categoriesProvider.stream().filter((category) -> {
			return category.getName().equalsIgnoreCase(categoryName);
		}).collect(Collectors.toList());
		List<Product> products = new ArrayList<>();
		if (categories != null && !categories.isEmpty()) {
			products = categories.get(0).getProducts();
		}
		return products;

	}

	private List<ICategoryProvider> findCategoryProviders() throws InvalidSyntaxException {
		List<ICategoryProvider> categoriesProvider = new ArrayList<>();
		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ServiceReference<?>[] references= bundleContext.getAllServiceReferences(ICategoryProvider.class.getName(), null);
		for(ServiceReference reference : references) {
			ICategoryProvider provider=(ICategoryProvider)bundleContext.getService(reference);
			categoriesProvider.add(provider);
		}
		return categoriesProvider;
	}

	@Override
	public Source getSource() {
		return Source.HTTP;
	}

}

	@Bean
	public ViewResolutionResultHandler viewResolutionResultHandler() {
		ViewResolverRegistry registry = getViewResolverRegistry();
		List<ViewResolver> resolvers = registry.getViewResolvers();
		ViewResolutionResultHandler handler = new ViewResolutionResultHandler(
				resolvers, webFluxContentTypeResolver(), webFluxAdapterRegistry());
		handler.setDefaultViews(registry.getDefaultViews());
		handler.setOrder(registry.getOrder());
		return handler;
	}

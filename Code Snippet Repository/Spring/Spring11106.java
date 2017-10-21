	@Bean
	public ServerResponseResultHandler serverResponseResultHandler() {
		ViewResolverRegistry registry = getViewResolverRegistry();
		List<ViewResolver> resolvers = registry.getViewResolvers();

		ServerResponseResultHandler handler = new ServerResponseResultHandler();
		handler.setMessageWriters(serverCodecConfigurer().getWriters());
		handler.setViewResolvers(resolvers);
		handler.setOrder(registry.getOrder() + 1);

		return handler;
	}

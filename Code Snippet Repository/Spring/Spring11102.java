	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter adapter = createRequestMappingHandlerAdapter();
		adapter.setMessageReaders(serverCodecConfigurer().getReaders());
		adapter.setWebBindingInitializer(getConfigurableWebBindingInitializer());
		adapter.setReactiveAdapterRegistry(webFluxAdapterRegistry());

		ArgumentResolverConfigurer configurer = new ArgumentResolverConfigurer();
		configureArgumentResolvers(configurer);
		adapter.setArgumentResolverConfigurer(configurer);

		return adapter;
	}

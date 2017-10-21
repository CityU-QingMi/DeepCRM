	@Bean
	public HandlerMapping resourceHandlerMapping() {
		ResourceLoader resourceLoader = this.applicationContext;
		if (resourceLoader == null) {
			resourceLoader = new DefaultResourceLoader();
		}
		ResourceHandlerRegistry registry = new ResourceHandlerRegistry(resourceLoader);
		addResourceHandlers(registry);

		AbstractHandlerMapping handlerMapping = registry.getHandlerMapping();
		if (handlerMapping != null) {
			PathMatchConfigurer configurer = getPathMatchConfigurer();
			Boolean useTrailingSlashMatch = configurer.isUseTrailingSlashMatch();
			Boolean useCaseSensitiveMatch = configurer.isUseCaseSensitiveMatch();
			if (useTrailingSlashMatch != null) {
				handlerMapping.setUseTrailingSlashMatch(useTrailingSlashMatch);
			}
			if (useCaseSensitiveMatch != null) {
				handlerMapping.setUseCaseSensitiveMatch(useCaseSensitiveMatch);
			}
		}
		else {
			handlerMapping = new EmptyHandlerMapping();
		}
		return handlerMapping;
	}

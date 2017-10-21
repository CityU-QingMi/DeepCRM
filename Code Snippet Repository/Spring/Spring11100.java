	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping mapping = createRequestMappingHandlerMapping();
		mapping.setOrder(0);
		mapping.setContentTypeResolver(webFluxContentTypeResolver());
		mapping.setCorsConfigurations(getCorsConfigurations());

		PathMatchConfigurer configurer = getPathMatchConfigurer();
		Boolean useTrailingSlashMatch = configurer.isUseTrailingSlashMatch();
		Boolean useCaseSensitiveMatch = configurer.isUseCaseSensitiveMatch();
		if (useTrailingSlashMatch != null) {
			mapping.setUseTrailingSlashMatch(useTrailingSlashMatch);
		}
		if (useCaseSensitiveMatch != null) {
			mapping.setUseCaseSensitiveMatch(useCaseSensitiveMatch);
		}
		return mapping;
	}

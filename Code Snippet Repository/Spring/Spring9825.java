	public static WebHttpHandlerBuilder applicationContext(ApplicationContext context) {
		WebHttpHandlerBuilder builder = new WebHttpHandlerBuilder(
				context.getBean(WEB_HANDLER_BEAN_NAME, WebHandler.class));

		// Autowire lists for @Bean + @Order

		SortedBeanContainer container = new SortedBeanContainer();
		context.getAutowireCapableBeanFactory().autowireBean(container);
		builder.filters(filters -> filters.addAll(container.getFilters()));
		builder.exceptionHandlers(handlers -> handlers.addAll(container.getExceptionHandlers()));

		try {
			builder.sessionManager(
					context.getBean(WEB_SESSION_MANAGER_BEAN_NAME, WebSessionManager.class));
		}
		catch (NoSuchBeanDefinitionException ex) {
			// Fall back on default
		}

		try {
			builder.codecConfigurer(
					context.getBean(SERVER_CODEC_CONFIGURER_BEAN_NAME, ServerCodecConfigurer.class));
		}
		catch (NoSuchBeanDefinitionException ex) {
			// Fall back on default
		}

		try {
			builder.localeContextResolver(
					context.getBean(LOCALE_CONTEXT_RESOLVER_BEAN_NAME, LocaleContextResolver.class));
		}
		catch (NoSuchBeanDefinitionException ex) {
			// Fall back on default
		}

		return builder;
	}

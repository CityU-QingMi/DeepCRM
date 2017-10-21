	@SuppressWarnings("")
	protected WebApplicationContext initServlet(
			final ApplicationContextInitializer<GenericWebApplicationContext> initializer,
			final Class<?>... controllerClasses) throws ServletException {

		final GenericWebApplicationContext wac = new GenericWebApplicationContext();

		servlet = new DispatcherServlet() {
			@Override
			protected WebApplicationContext createWebApplicationContext(@Nullable WebApplicationContext parent) {
				for (Class<?> clazz : controllerClasses) {
					wac.registerBeanDefinition(clazz.getSimpleName(), new RootBeanDefinition(clazz));
				}

				RootBeanDefinition mappingDef = new RootBeanDefinition(RequestMappingHandlerMapping.class);
				mappingDef.getPropertyValues().add("removeSemicolonContent", "false");
				wac.registerBeanDefinition("handlerMapping", mappingDef);

				wac.registerBeanDefinition("handlerAdapter", new RootBeanDefinition(RequestMappingHandlerAdapter.class));

				wac.registerBeanDefinition("requestMappingResolver", new RootBeanDefinition(ExceptionHandlerExceptionResolver.class));

				wac.registerBeanDefinition("responseStatusResolver", new RootBeanDefinition(ResponseStatusExceptionResolver.class));

				wac.registerBeanDefinition("defaultResolver", new RootBeanDefinition(DefaultHandlerExceptionResolver.class));

				if (initializer != null) {
					initializer.initialize(wac);
				}

				wac.refresh();
				return wac;
			}
		};

		servlet.init(new MockServletConfig());

		return wac;
	}

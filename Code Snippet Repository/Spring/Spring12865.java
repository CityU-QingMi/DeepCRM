	public HandlerMethodAnnotationDetectionTests(final Class<?> controllerType, boolean useAutoProxy) {
		GenericWebApplicationContext context = new GenericWebApplicationContext();
		context.registerBeanDefinition("controller", new RootBeanDefinition(controllerType));
		context.registerBeanDefinition("handlerMapping", new RootBeanDefinition(RequestMappingHandlerMapping.class));
		context.registerBeanDefinition("handlerAdapter", new RootBeanDefinition(RequestMappingHandlerAdapter.class));
		context.registerBeanDefinition("exceptionResolver", new RootBeanDefinition(ExceptionHandlerExceptionResolver.class));
		if (useAutoProxy) {
			DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
			autoProxyCreator.setBeanFactory(context.getBeanFactory());
			context.getBeanFactory().addBeanPostProcessor(autoProxyCreator);
			context.registerBeanDefinition("controllerAdvice", new RootBeanDefinition(ControllerAdvisor.class));
		}
		context.refresh();

		this.handlerMapping = context.getBean(RequestMappingHandlerMapping.class);
		this.handlerAdapter = context.getBean(RequestMappingHandlerAdapter.class);
		this.exceptionResolver = context.getBean(ExceptionHandlerExceptionResolver.class);
		context.close();
	}

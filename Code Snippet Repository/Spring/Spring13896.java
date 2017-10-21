	protected void registerEndpoints() {
		Set<Class<?>> endpointClasses = new LinkedHashSet<>();
		if (this.annotatedEndpointClasses != null) {
			endpointClasses.addAll(this.annotatedEndpointClasses);
		}

		ApplicationContext context = getApplicationContext();
		if (context != null) {
			String[] endpointBeanNames = context.getBeanNamesForAnnotation(ServerEndpoint.class);
			for (String beanName : endpointBeanNames) {
				endpointClasses.add(context.getType(beanName));
			}
		}

		for (Class<?> endpointClass : endpointClasses) {
			registerEndpoint(endpointClass);
		}

		if (context != null) {
			Map<String, ServerEndpointConfig> endpointConfigMap = context.getBeansOfType(ServerEndpointConfig.class);
			for (ServerEndpointConfig endpointConfig : endpointConfigMap.values()) {
				registerEndpoint(endpointConfig);
			}
		}
	}

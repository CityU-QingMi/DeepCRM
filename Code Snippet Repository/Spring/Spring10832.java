	private void runTest(Object controller) throws Exception {
		HandlerMethodArgumentResolverComposite resolvers = new HandlerMethodArgumentResolverComposite();
		resolvers.addResolver(new ModelAttributeMethodProcessor(false));
		resolvers.addResolver(new ModelMethodProcessor());
		WebDataBinderFactory dataBinderFactory = new DefaultDataBinderFactory(null);

		Class<?> type = controller.getClass();
		Set<Method> methods = MethodIntrospector.selectMethods(type, METHOD_FILTER);
		List<InvocableHandlerMethod> modelMethods = new ArrayList<>();
		for (Method method : methods) {
			InvocableHandlerMethod modelMethod = new InvocableHandlerMethod(controller, method);
			modelMethod.setHandlerMethodArgumentResolvers(resolvers);
			modelMethod.setDataBinderFactory(dataBinderFactory);
			modelMethods.add(modelMethod);
		}
		Collections.shuffle(modelMethods);

		SessionAttributesHandler sessionHandler = new SessionAttributesHandler(type, this.sessionAttributeStore);
		ModelFactory factory = new ModelFactory(modelMethods, dataBinderFactory, sessionHandler);
		factory.initModel(this.webRequest, this.mavContainer, new HandlerMethod(controller, "handle"));
		if (logger.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder();
			for (String name : getInvokedMethods()) {
				sb.append(" >> ").append(name);
			}
			logger.debug(sb);
		}
	}

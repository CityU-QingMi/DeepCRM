	private ModelFactory createModelFactory(String methodName, Class<?>... parameterTypes) throws Exception {
		HandlerMethodArgumentResolverComposite resolvers = new HandlerMethodArgumentResolverComposite();
		resolvers.addResolver(new ModelMethodProcessor());

		InvocableHandlerMethod modelMethod = createHandlerMethod(methodName, parameterTypes);
		modelMethod.setHandlerMethodArgumentResolvers(resolvers);
		modelMethod.setDataBinderFactory(null);
		modelMethod.setParameterNameDiscoverer(new LocalVariableTableParameterNameDiscoverer());

		return new ModelFactory(Collections.singletonList(modelMethod), null, this.attributeHandler);
	}

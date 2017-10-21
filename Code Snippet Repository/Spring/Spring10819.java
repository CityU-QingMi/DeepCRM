	private WebDataBinderFactory createFactory(String methodName, Class<?>... parameterTypes)
			throws Exception {

		Object handler = new InitBinderHandler();
		Method method = handler.getClass().getMethod(methodName, parameterTypes);

		InvocableHandlerMethod handlerMethod = new InvocableHandlerMethod(handler, method);
		handlerMethod.setHandlerMethodArgumentResolvers(this.argumentResolvers);
		handlerMethod.setDataBinderFactory(new DefaultDataBinderFactory(null));
		handlerMethod.setParameterNameDiscoverer(new LocalVariableTableParameterNameDiscoverer());

		return new InitBinderDataBinderFactory(
				Collections.singletonList(handlerMethod), this.bindingInitializer);
	}

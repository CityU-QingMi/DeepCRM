	private BindingContext createBindingContext(String methodName, Class<?>... parameterTypes) throws Exception {
		Object handler = new InitBinderHandler();
		Method method = handler.getClass().getMethod(methodName, parameterTypes);

		SyncInvocableHandlerMethod handlerMethod = new SyncInvocableHandlerMethod(handler, method);
		handlerMethod.setArgumentResolvers(new ArrayList<>(this.argumentResolvers));
		handlerMethod.setParameterNameDiscoverer(new LocalVariableTableParameterNameDiscoverer());

		return new InitBinderBindingContext(this.bindingInitializer, Collections.singletonList(handlerMethod));
	}

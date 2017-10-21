	private InitBinderBindingContext getBindingContext(Object controller) {

		List<SyncInvocableHandlerMethod> binderMethods =
				MethodIntrospector.selectMethods(controller.getClass(), BINDER_METHODS)
						.stream()
						.map(method -> new SyncInvocableHandlerMethod(controller, method))
						.collect(Collectors.toList());;

		WebBindingInitializer bindingInitializer = new ConfigurableWebBindingInitializer();
		return new InitBinderBindingContext(bindingInitializer, binderMethods);
	}

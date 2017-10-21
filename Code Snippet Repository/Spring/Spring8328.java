	public ResultMatcher handlerType(final Class<?> type) {
		return result -> {
			Object handler = result.getHandler();
			assertTrue("No handler", handler != null);
			if (handler != null) {
				Class<?> actual = handler.getClass();
				if (HandlerMethod.class.isInstance(handler)) {
					actual = ((HandlerMethod) handler).getBeanType();
				}
				assertEquals("Handler type", type, ClassUtils.getUserClass(actual));
			}
		};
	}

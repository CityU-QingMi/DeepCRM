	private CorsConfiguration getCorsConfiguration(HandlerExecutionChain chain, boolean isPreFlightRequest) {
		if (isPreFlightRequest) {
			Object handler = chain.getHandler();
			assertTrue(handler.getClass().getSimpleName().equals("PreFlightHandler"));
			DirectFieldAccessor accessor = new DirectFieldAccessor(handler);
			return (CorsConfiguration)accessor.getPropertyValue("config");
		}
		else {
			HandlerInterceptor[] interceptors = chain.getInterceptors();
			if (interceptors != null) {
				for (HandlerInterceptor interceptor : interceptors) {
					if (interceptor.getClass().getSimpleName().equals("CorsInterceptor")) {
						DirectFieldAccessor accessor = new DirectFieldAccessor(interceptor);
						return (CorsConfiguration) accessor.getPropertyValue("config");
					}
				}
			}
		}
		return null;
	}

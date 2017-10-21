	@Override
	@Nullable
	public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		Assert.notNull(this.handlerMappings, "Handler mappings not initialized");
		HttpServletRequest wrapper = new RequestAttributeChangeIgnoringWrapper(request);
		for (HandlerMapping handlerMapping : this.handlerMappings) {
			HandlerExecutionChain handler = null;
			try {
				handler = handlerMapping.getHandler(wrapper);
			}
			catch (Exception ex) {
				// Ignore
			}
			if (handler == null) {
				continue;
			}
			if (handler.getInterceptors() != null) {
				for (HandlerInterceptor interceptor : handler.getInterceptors()) {
					if (interceptor instanceof CorsConfigurationSource) {
						return ((CorsConfigurationSource) interceptor).getCorsConfiguration(wrapper);
					}
				}
			}
			if (handler.getHandler() instanceof CorsConfigurationSource) {
				return ((CorsConfigurationSource) handler.getHandler()).getCorsConfiguration(wrapper);
			}
		}
		return null;
	}

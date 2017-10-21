		@Override
		protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
			if (handlerExceptionResolvers == null) {
				return;
			}
			for (HandlerExceptionResolver resolver : handlerExceptionResolvers) {
				if (resolver instanceof ApplicationContextAware) {
					ApplicationContext applicationContext  = getApplicationContext();
					if (applicationContext != null) {
						((ApplicationContextAware) resolver).setApplicationContext(applicationContext);
					}
				}
				if (resolver instanceof InitializingBean) {
					try {
						((InitializingBean) resolver).afterPropertiesSet();
					}
					catch (Exception ex) {
						throw new IllegalStateException("Failure from afterPropertiesSet", ex);
					}
				}
				exceptionResolvers.add(resolver);
			}
		}

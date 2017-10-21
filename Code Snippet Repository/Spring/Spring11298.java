	private Mono<HandlerResult> handleException(Throwable exception, HandlerMethod handlerMethod,
			BindingContext bindingContext, ServerWebExchange exchange) {

		Assert.state(this.methodResolver != null, "Not initialized");

		InvocableHandlerMethod invocable = this.methodResolver.getExceptionHandlerMethod(exception, handlerMethod);
		if (invocable != null) {
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("Invoking @ExceptionHandler method: " + invocable.getMethod());
				}
				bindingContext.getModel().asMap().clear();
				Throwable cause = exception.getCause();
				if (cause != null) {
					return invocable.invoke(exchange, bindingContext, exception, cause, handlerMethod);
				}
				else {
					return invocable.invoke(exchange, bindingContext, exception, handlerMethod);
				}
			}
			catch (Throwable invocationEx) {
				if (logger.isWarnEnabled()) {
					logger.warn("Failed to invoke: " + invocable.getMethod(), invocationEx);
				}
			}
		}
		return Mono.error(exception);
	}

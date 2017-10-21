	protected void processHandlerMethodException(HandlerMethod handlerMethod, Exception ex, Message<?> message) {
		InvocableHandlerMethod invocable = getExceptionHandlerMethod(handlerMethod, ex);
		if (invocable == null) {
			logger.error("Unhandled exception from message handler method", ex);
			return;
		}
		invocable.setMessageMethodArgumentResolvers(this.argumentResolvers);
		if (logger.isDebugEnabled()) {
			logger.debug("Invoking " + invocable.getShortLogMessage());
		}
		try {
			Object returnValue = invocable.invoke(message, ex, handlerMethod);
			MethodParameter returnType = invocable.getReturnType();
			if (void.class == returnType.getParameterType()) {
				return;
			}
			this.returnValueHandlers.handleReturnValue(returnValue, returnType, message);
		}
		catch (Throwable ex2) {
			logger.error("Error while processing handler method exception", ex2);
		}
	}

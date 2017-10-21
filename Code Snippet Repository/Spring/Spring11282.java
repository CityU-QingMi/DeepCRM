	private void invokeBinderMethod(WebExchangeDataBinder dataBinder,
			ServerWebExchange exchange, SyncInvocableHandlerMethod binderMethod) {

		HandlerResult result = binderMethod.invokeForHandlerResult(
				exchange, this.binderMethodContext, dataBinder);

		if (result != null && result.getReturnValue() != null) {
			throw new IllegalStateException(
					"@InitBinder methods should return void: " + binderMethod);
		}

		// Should not happen (no Model argument resolution) ...
		if (!this.binderMethodContext.getModel().asMap().isEmpty()) {
			throw new IllegalStateException(
					"@InitBinder methods should not add model attributes: " + binderMethod);
		}
	}

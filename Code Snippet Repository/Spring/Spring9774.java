	private boolean isAsyncReturnValue(@Nullable Object value, MethodParameter returnType) {
		for (HandlerMethodReturnValueHandler handler : this.returnValueHandlers) {
			if (handler instanceof AsyncHandlerMethodReturnValueHandler) {
				if (((AsyncHandlerMethodReturnValueHandler) handler).isAsyncReturnValue(value, returnType)) {
					return true;
				}
			}
		}
		return false;
	}

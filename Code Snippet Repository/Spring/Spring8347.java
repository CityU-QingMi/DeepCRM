	protected void printHandler(@Nullable Object handler, @Nullable HandlerInterceptor[] interceptors) throws Exception {
		if (handler == null) {
			this.printer.printValue("Type", null);
		}
		else {
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				this.printer.printValue("Type", handlerMethod.getBeanType().getName());
				this.printer.printValue("Method", handlerMethod);
			}
			else {
				this.printer.printValue("Type", handler.getClass().getName());
			}
		}
	}

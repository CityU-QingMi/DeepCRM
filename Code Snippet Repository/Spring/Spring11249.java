	public Mono<HandlerResult> invoke(ServerWebExchange exchange, BindingContext bindingContext,
			Object... providedArgs) {

		return resolveArguments(exchange, bindingContext, providedArgs).flatMap(args -> {
			try {
				Object value = doInvoke(args);
				HandlerResult result = new HandlerResult(this, value, getReturnType(), bindingContext);
				HttpStatus status = getResponseStatus();
				if (status != null) {
					exchange.getResponse().setStatusCode(status);
				}
				return Mono.just(result);
			}
			catch (InvocationTargetException ex) {
				return Mono.error(ex.getTargetException());
			}
			catch (Throwable ex) {
				return Mono.error(new IllegalStateException(getInvocationErrorMessage(args)));
			}
		});
	}

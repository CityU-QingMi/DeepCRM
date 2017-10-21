	private Mono<Object> resolveArg(HandlerMethodArgumentResolver resolver, MethodParameter parameter,
			BindingContext bindingContext, ServerWebExchange exchange) {

		try {
			return resolver.resolveArgument(parameter, bindingContext, exchange)
					.defaultIfEmpty(NO_ARG_VALUE)
					.doOnError(cause -> {
						if (logger.isDebugEnabled()) {
							logger.debug(getDetailedErrorMessage("Failed to resolve", parameter), cause);
						}
					});
		}
		catch (Exception ex) {
			throw getArgumentError("Failed to resolve", parameter, ex);
		}
	}

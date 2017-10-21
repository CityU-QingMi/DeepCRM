	@SuppressWarnings("")
	public Mono<Void> initModel(HandlerMethod handlerMethod, InitBinderBindingContext bindingContext,
			ServerWebExchange exchange) {

		List<InvocableHandlerMethod> modelMethods =
				this.methodResolver.getModelAttributeMethods(handlerMethod);

		SessionAttributesHandler sessionAttributesHandler =
				this.methodResolver.getSessionAttributesHandler(handlerMethod);

		if (!sessionAttributesHandler.hasSessionAttributes()) {
			return invokeModelAttributeMethods(bindingContext, modelMethods, exchange);
		}

		return exchange.getSession()
				.flatMap(session -> {
					Map<String, Object> attributes = sessionAttributesHandler.retrieveAttributes(session);
					bindingContext.getModel().mergeAttributes(attributes);
					bindingContext.setSessionContext(sessionAttributesHandler, session);
					return invokeModelAttributeMethods(bindingContext, modelMethods, exchange)
							.doOnSuccess(aVoid -> {
								findModelAttributes(handlerMethod, sessionAttributesHandler).forEach(name -> {
									if (!bindingContext.getModel().containsAttribute(name)) {
										Object value = session.getRequiredAttribute(name);
										bindingContext.getModel().addAttribute(name, value);
									}
								});
							});
				});
	}

	private Mono<Void> invokeModelAttributeMethods(BindingContext bindingContext,
			List<InvocableHandlerMethod> modelMethods, ServerWebExchange exchange) {

		List<Mono<HandlerResult>> resultList = new ArrayList<>();
		modelMethods.forEach(invocable -> resultList.add(invocable.invoke(exchange, bindingContext)));

		return Mono
				.zip(resultList, objectArray ->
						Arrays.stream(objectArray)
								.map(object -> handleResult(((HandlerResult) object), bindingContext))
								.collect(Collectors.toList()))
				.flatMap(Mono::when);
	}

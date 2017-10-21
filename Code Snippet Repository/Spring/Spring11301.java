	@Override
	public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext,
			ServerWebExchange exchange) {

		RequestPart requestPart = parameter.getParameterAnnotation(RequestPart.class);
		boolean isRequired = (requestPart == null || requestPart.required());
		String name = getPartName(parameter, requestPart);

		Flux<Part> partFlux = getPartValues(name, exchange);
		if (isRequired) {
			partFlux = partFlux.switchIfEmpty(Flux.error(getMissingPartException(name, parameter)));
		}

		ReactiveAdapter adapter = getAdapterRegistry().getAdapter(parameter.getParameterType());
		MethodParameter elementType = adapter != null ? parameter.nested() : parameter;

		if (Part.class.isAssignableFrom(elementType.getNestedParameterType())) {
			if (adapter != null) {
				partFlux = adapter.isMultiValue() ? partFlux : partFlux.take(1);
				return Mono.just(adapter.fromPublisher(partFlux));
			}
			else {
				return partFlux.next().cast(Object.class);
			}
		}

		return partFlux.next().flatMap(part -> {
			ServerHttpRequest partRequest = new PartServerHttpRequest(exchange.getRequest(), part);
			ServerWebExchange partExchange = exchange.mutate().request(partRequest).build();
			return readBody(parameter, isRequired, bindingContext, partExchange);
		});
	}

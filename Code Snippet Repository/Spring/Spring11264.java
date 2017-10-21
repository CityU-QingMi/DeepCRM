	@SuppressWarnings("")
	protected Mono<Void> writeBody(@Nullable Object body, MethodParameter bodyParameter, ServerWebExchange exchange) {
		ResolvableType bodyType = ResolvableType.forMethodParameter(bodyParameter);
		Class<?> bodyClass = bodyType.resolve();
		ReactiveAdapter adapter = getAdapterRegistry().getAdapter(bodyClass, body);

		Publisher<?> publisher;
		ResolvableType elementType;
		if (adapter != null) {
			publisher = adapter.toPublisher(body);
			ResolvableType genericType = bodyType.getGeneric(0);
			elementType = getElementType(adapter, genericType);
		}
		else {
			publisher = Mono.justOrEmpty(body);
			elementType = ((bodyClass == null || bodyClass.equals(Object.class)) && body != null ?
					ResolvableType.forInstance(body) : bodyType);
		}

		if (void.class == elementType.getRawClass() || Void.class == elementType.getRawClass()) {
			return Mono.from((Publisher<Void>) publisher);
		}

		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();
		MediaType bestMediaType = selectMediaType(exchange, () -> getProducibleMediaTypes(elementType));
		if (bestMediaType != null) {
			for (HttpMessageWriter<?> writer : getMessageWriters()) {
				if (writer.canWrite(elementType, bestMediaType)) {
					return writer.write((Publisher) publisher, bodyType, elementType,
							bestMediaType, request, response, Collections.emptyMap());
				}
			}
		}
		else {
			if (getProducibleMediaTypes(elementType).isEmpty()) {
				return Mono.error(new IllegalStateException("No converter for return value type: " + elementType));
			}
		}

		return Mono.error(new NotAcceptableStatusException(getProducibleMediaTypes(elementType)));
	}

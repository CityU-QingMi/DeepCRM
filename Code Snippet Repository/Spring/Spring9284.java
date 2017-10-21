	@Override
	public Mono<T> readMono(ResolvableType actualType, ResolvableType elementType,
			ServerHttpRequest request, ServerHttpResponse response, Map<String, Object> hints) {

		Map<String, Object> allHints = new HashMap<>(4);
		allHints.putAll(getReadHints(actualType, elementType, request, response));
		allHints.putAll(hints);

		return readMono(elementType, request, allHints);
	}

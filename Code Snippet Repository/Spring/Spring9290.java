	@Override
	public Mono<Void> write(Publisher<? extends T> inputStream, ResolvableType actualType,
			ResolvableType elementType, @Nullable MediaType mediaType, ServerHttpRequest request,
			ServerHttpResponse response, Map<String, Object> hints) {

		Map<String, Object> allHints = new HashMap<>();
		allHints.putAll(getWriteHints(actualType, elementType, mediaType, request, response));
		allHints.putAll(hints);

		return write(inputStream, elementType, mediaType, response, allHints);
	}

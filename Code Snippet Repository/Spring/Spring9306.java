	@Override
	public Mono<Void> write(Publisher<?> input, ResolvableType actualType, ResolvableType elementType,
			@Nullable MediaType mediaType, ServerHttpRequest request, ServerHttpResponse response,
			Map<String, Object> hints) {

		Map<String, Object> allHints = new HashMap<>();
		allHints.putAll(getEncodeHints(actualType, elementType, mediaType, request, response));
		allHints.putAll(hints);

		return write(input, elementType, mediaType, response, allHints);
	}

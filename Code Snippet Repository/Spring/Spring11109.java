	@SuppressWarnings("")
	static <T> BodyExtractor<Flux<T>, ReactiveHttpInputMessage> toFlux(ResolvableType elementType) {
		Assert.notNull(elementType, "'elementType' must not be null");
		return (inputMessage, context) -> readWithMessageReaders(inputMessage, context,
				elementType,
				(HttpMessageReader<T> reader) -> {
					Optional<ServerHttpResponse> serverResponse = context.serverResponse();
					if (serverResponse.isPresent() && inputMessage instanceof ServerHttpRequest) {
						return reader.read(elementType, elementType, (ServerHttpRequest) inputMessage,
								serverResponse.get(), context.hints());
					}
					else {
						return reader.read(elementType, inputMessage, context.hints());
					}
				},
				ex -> (inputMessage.getHeaders().getContentType() == null) ?
						permitEmptyOrFail(inputMessage, ex) : Flux.error(ex),
				Flux::empty);
	}

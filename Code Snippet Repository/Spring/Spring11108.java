	static <T> BodyExtractor<Mono<T>, ReactiveHttpInputMessage> toMono(ResolvableType elementType) {
		Assert.notNull(elementType, "'elementType' must not be null");
		return (inputMessage, context) -> readWithMessageReaders(inputMessage, context,
				elementType,
				(HttpMessageReader<T> reader) -> {
					Optional<ServerHttpResponse> serverResponse = context.serverResponse();
					if (serverResponse.isPresent() && inputMessage instanceof ServerHttpRequest) {
						return reader.readMono(elementType, elementType, (ServerHttpRequest) inputMessage,
								serverResponse.get(), context.hints());
					}
					else {
						return reader.readMono(elementType, inputMessage, context.hints());
					}
				},
				ex -> (inputMessage.getHeaders().getContentType() == null) ?
						Mono.from(permitEmptyOrFail(inputMessage, ex)) : Mono.error(ex),
				Mono::empty);
	}

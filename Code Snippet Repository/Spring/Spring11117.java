	private static <T, P extends Publisher<?>, M extends ReactiveHttpOutputMessage> BodyInserter<T, M> bodyInserterFor(
			P body, ResolvableType bodyType) {

		return (outputMessage, context) -> {
			MediaType contentType = outputMessage.getHeaders().getContentType();
			List<HttpMessageWriter<?>> messageWriters = context.messageWriters();
			return messageWriters.stream()
					.filter(messageWriter -> messageWriter.canWrite(bodyType, contentType))
					.findFirst()
					.map(BodyInserters::cast)
					.map(messageWriter -> {
						Optional<ServerHttpRequest> serverRequest = context.serverRequest();
						if (serverRequest.isPresent() && outputMessage instanceof ServerHttpResponse) {
							return messageWriter.write(body, bodyType, bodyType, contentType,
									serverRequest.get(), (ServerHttpResponse) outputMessage,
									context.hints());
						} else {
							return messageWriter.write(body, bodyType, contentType, outputMessage,
											context.hints());
						}
					})
					.orElseGet(() -> {
						List<MediaType> supportedMediaTypes = messageWriters.stream()
								.flatMap(reader -> reader.getWritableMediaTypes().stream())
								.collect(Collectors.toList());
						UnsupportedMediaTypeException error =
								new UnsupportedMediaTypeException(contentType, supportedMediaTypes);
						return Mono.error(error);
					});
		};
	}

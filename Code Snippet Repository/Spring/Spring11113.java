	public static <T extends Resource> BodyInserter<T, ReactiveHttpOutputMessage> fromResource(T resource) {
		Assert.notNull(resource, "'resource' must not be null");
		return (outputMessage, context) -> {
			Mono<T> inputStream = Mono.just(resource);
			HttpMessageWriter<Resource> messageWriter = resourceHttpMessageWriter(context);
			Optional<ServerHttpRequest> serverRequest = context.serverRequest();
			if (serverRequest.isPresent() && outputMessage instanceof ServerHttpResponse) {
				return messageWriter.write(inputStream, RESOURCE_TYPE, RESOURCE_TYPE, null,
						serverRequest.get(), (ServerHttpResponse) outputMessage, context.hints());
			}
			else {
				return messageWriter.write(inputStream, RESOURCE_TYPE, null, outputMessage, context.hints());
			}
		};
	}

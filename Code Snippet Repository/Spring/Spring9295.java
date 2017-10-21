	private Mono<Void> writeResource(Resource resource, ResolvableType type, @Nullable MediaType mediaType,
			ReactiveHttpOutputMessage message, Map<String, Object> hints) {

		HttpHeaders headers = message.getHeaders();
		MediaType resourceMediaType = getResourceMediaType(mediaType, resource);
		headers.setContentType(resourceMediaType);

		if (headers.getContentLength() < 0) {
			lengthOf(resource).ifPresent(headers::setContentLength);
		}

		return zeroCopy(resource, null, message)
				.orElseGet(() -> {
					Mono<Resource> input = Mono.just(resource);
					DataBufferFactory factory = message.bufferFactory();
					Flux<DataBuffer> body = this.encoder.encode(input, factory, type, resourceMediaType, hints);
					return message.writeWith(body);
				});
	}

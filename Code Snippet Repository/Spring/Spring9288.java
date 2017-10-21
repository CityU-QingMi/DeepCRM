	@Override
	public Mono<Void> write(Publisher<? extends T> inputStream, ResolvableType elementType,
			@Nullable MediaType mediaType, ReactiveHttpOutputMessage message, Map<String, Object> hints) {

		MediaType contentType = updateContentType(message, mediaType);

		Flux<DataBuffer> body = this.encoder.encode(
				inputStream, message.bufferFactory(), elementType, contentType, hints);

		return (isStreamingMediaType(contentType) ?
				message.writeAndFlushWith(body.map(Flux::just)) : message.writeWith(body));
	}

	@Override
	public Mono<Object> readMono(ResolvableType elementType, ReactiveHttpInputMessage message,
			Map<String, Object> hints) {

		// We're ahead of String + "*/*"
		// Let's see if we can aggregate the output (lest we time out)...

		if (String.class.equals(elementType.getRawClass())) {
			Flux<DataBuffer> body = message.getBody();
			return stringDecoder.decodeToMono(body, elementType, null, null).cast(Object.class);
		}

		return Mono.error(new UnsupportedOperationException(
				"ServerSentEventHttpMessageReader only supports reading stream of events as a Flux"));
	}

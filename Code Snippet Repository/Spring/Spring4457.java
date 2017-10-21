	@Override
	public Flux<String> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType,
			@Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {

		Flux<DataBuffer> inputFlux = Flux.from(inputStream);
		if (this.splitOnNewline) {
			inputFlux = Flux.from(inputStream).flatMap(StringDecoder::splitOnNewline);
		}
		return inputFlux.map(buffer ->  decodeDataBuffer(buffer, mimeType));
	}

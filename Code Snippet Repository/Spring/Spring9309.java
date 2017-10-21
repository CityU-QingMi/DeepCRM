	@Override
	public Flux<DataBuffer> encode(Publisher<?> inputStream, DataBufferFactory bufferFactory,
			ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {

		Assert.notNull(inputStream, "'inputStream' must not be null");
		Assert.notNull(bufferFactory, "'bufferFactory' must not be null");
		Assert.notNull(elementType, "'elementType' must not be null");

		if (inputStream instanceof Mono) {
			return Flux.from(inputStream).map(value ->
					encodeValue(value, mimeType, bufferFactory, elementType, hints));
		}
		else if (this.streamingMediaTypes.stream().anyMatch(streamingMediaType -> streamingMediaType.isCompatibleWith(mimeType))) {
			return Flux.from(inputStream).map(value -> {
				DataBuffer buffer = encodeValue(value, mimeType, bufferFactory, elementType, hints);
				buffer.write(new byte[]{'\n'});
				return buffer;
			});
		}
		else {
			ResolvableType listType = ResolvableType.forClassWithGenerics(List.class, elementType);
			return Flux.from(inputStream).collectList().map(list ->
					encodeValue(list, mimeType, bufferFactory, listType, hints)).flux();
		}
	}

	@Override
	public Flux<ByteBuffer> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType,
			@Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {

		return Flux.from(inputStream).map((dataBuffer) -> {
			ByteBuffer copy = ByteBuffer.allocate(dataBuffer.readableByteCount());
			copy.put(dataBuffer.asByteBuffer());
			copy.flip();
			DataBufferUtils.release(dataBuffer);
			return copy;
		});
	}

	@Override
	public Flux<byte[]> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType,
			@Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {

		return Flux.from(inputStream).map((dataBuffer) -> {
			byte[] result = new byte[dataBuffer.readableByteCount()];
			dataBuffer.read(result);
			DataBufferUtils.release(dataBuffer);
			return result ;
		});
	}

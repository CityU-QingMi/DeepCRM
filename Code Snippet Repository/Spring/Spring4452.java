	@Override
	public Flux<DataBuffer> encode(Publisher<? extends CharSequence> inputStream,
			DataBufferFactory bufferFactory, ResolvableType elementType,
			@Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {

		Charset charset;
		if (mimeType != null && mimeType.getCharset() != null) {
			charset = mimeType.getCharset();
		}
		else {
			 charset = DEFAULT_CHARSET;
		}
		return Flux.from(inputStream).map(charSequence -> {
			CharBuffer charBuffer = CharBuffer.wrap(charSequence);
			ByteBuffer byteBuffer = charset.encode(charBuffer);
			return bufferFactory.wrap(byteBuffer);
		});
	}

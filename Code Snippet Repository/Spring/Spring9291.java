	@Override
	public Mono<MultiValueMap<String, String>> readMono(ResolvableType elementType,
			ReactiveHttpInputMessage message, Map<String, Object> hints) {

		MediaType contentType = message.getHeaders().getContentType();
		Charset charset = getMediaTypeCharset(contentType);

		return message.getBody()
				.reduce(DataBuffer::write)
				.map(buffer -> {
					CharBuffer charBuffer = charset.decode(buffer.asByteBuffer());
					String body = charBuffer.toString();
					DataBufferUtils.release(buffer);
					return parseFormData(charset, body);
				});
	}

	@Override
	public Mono<Void> write(Publisher<? extends MultiValueMap<String, String>> inputStream,
			ResolvableType elementType, @Nullable MediaType mediaType, ReactiveHttpOutputMessage message,
			Map<String, Object> hints) {

		MediaType contentType = message.getHeaders().getContentType();
		if (contentType == null) {
			contentType = MediaType.APPLICATION_FORM_URLENCODED;
			message.getHeaders().setContentType(contentType);
		}

		Charset charset = getMediaTypeCharset(contentType);

		return Flux
				.from(inputStream)
				.single()
				.map(form -> generateForm(form, charset))
				.flatMap(value -> {
					ByteBuffer byteBuffer = charset.encode(value);
					DataBuffer buffer = message.bufferFactory().wrap(byteBuffer);
					message.getHeaders().setContentLength(byteBuffer.remaining());
					return message.writeWith(Mono.just(buffer));
				});

	}

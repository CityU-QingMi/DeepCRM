	@Override
	public Mono<Void> write(Publisher<? extends MultiValueMap<String, ?>> inputStream,
			ResolvableType elementType, @Nullable MediaType mediaType, ReactiveHttpOutputMessage outputMessage,
			Map<String, Object> hints) {

		byte[] boundary = generateMultipartBoundary();

		Map<String, String> params = new HashMap<>(2);
		params.put("boundary", new String(boundary, StandardCharsets.US_ASCII));
		params.put("charset", getCharset().name());
		outputMessage.getHeaders().setContentType(new MediaType(MediaType.MULTIPART_FORM_DATA, params));

		return Mono.from(inputStream).flatMap(map -> {
			Flux<DataBuffer> body = Flux.fromIterable(map.entrySet())
					.concatMap(entry -> encodePartValues(boundary, entry.getKey(), entry.getValue()))
					.concatWith(Mono.just(generateLastLine(boundary)));
			return outputMessage.writeWith(body);
		});
	}

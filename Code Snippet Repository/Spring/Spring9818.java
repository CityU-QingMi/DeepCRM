	@SuppressWarnings("")
	private static Mono<MultiValueMap<String, Part>> initMultipartData(ServerHttpRequest request,
			ServerCodecConfigurer configurer) {

		try {
			MediaType contentType = request.getHeaders().getContentType();
			if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(contentType)) {
				return ((HttpMessageReader<MultiValueMap<String, Part>>) configurer.getReaders().stream()
						.filter(reader -> reader.canRead(MULTIPART_DATA_TYPE, MediaType.MULTIPART_FORM_DATA))
						.findFirst()
						.orElseThrow(() -> new IllegalStateException("No multipart HttpMessageReader.")))
						.readMono(MULTIPART_DATA_TYPE, request, Collections.emptyMap())
						.switchIfEmpty(EMPTY_MULTIPART_DATA)
						.cache();
			}
		}
		catch (InvalidMediaTypeException ex) {
			// Ignore
		}
		return EMPTY_MULTIPART_DATA;
	}

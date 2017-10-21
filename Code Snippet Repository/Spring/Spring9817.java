	@SuppressWarnings("")
	private static Mono<MultiValueMap<String, String>> initFormData(ServerHttpRequest request,
			ServerCodecConfigurer configurer) {

		try {
			MediaType contentType = request.getHeaders().getContentType();
			if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType)) {
				return ((HttpMessageReader<MultiValueMap<String, String>>) configurer.getReaders().stream()
						.filter(reader -> reader.canRead(FORM_DATA_TYPE, MediaType.APPLICATION_FORM_URLENCODED))
						.findFirst()
						.orElseThrow(() -> new IllegalStateException("No form data HttpMessageReader.")))
						.readMono(FORM_DATA_TYPE, request, Collections.emptyMap())
						.switchIfEmpty(EMPTY_FORM_DATA)
						.cache();
			}
		}
		catch (InvalidMediaTypeException ex) {
			// Ignore
		}
		return EMPTY_FORM_DATA;
	}

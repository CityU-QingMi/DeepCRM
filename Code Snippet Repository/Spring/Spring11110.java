	public static BodyExtractor<Mono<MultiValueMap<String, Part>>, ServerHttpRequest> toMultipartData() {
		return (serverRequest, context) -> {
			HttpMessageReader<MultiValueMap<String, Part>> messageReader =
					messageReader(MULTIPART_MAP_TYPE, MediaType.MULTIPART_FORM_DATA, context);
			return context.serverResponse()
					.map(serverResponse -> messageReader.readMono(MULTIPART_MAP_TYPE,
							MULTIPART_MAP_TYPE, serverRequest, serverResponse, context.hints()))
					.orElseGet(() -> messageReader.readMono(MULTIPART_MAP_TYPE, serverRequest, context.hints()));
		};
	}

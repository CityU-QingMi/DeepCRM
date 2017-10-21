	private ServerWebExchange exchangeMultipart(MultiValueMap<String, ?> multipartData) {

		MockClientHttpRequest request = new MockClientHttpRequest(HttpMethod.POST, "/");

		new MultipartHttpMessageWriter().write(Mono.just(multipartData), forClass(MultiValueMap.class),
				MediaType.MULTIPART_FORM_DATA, request, Collections.emptyMap()).block();

		return MockServerWebExchange.from(MockServerHttpRequest
				.post("/")
				.contentType(request.getHeaders().getContentType())
				.body(request.getBody()));
	}

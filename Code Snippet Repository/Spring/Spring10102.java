	private ServerHttpRequest generateMultipartRequest() throws IOException {
		HttpHeaders fooHeaders = new HttpHeaders();
		fooHeaders.setContentType(MediaType.TEXT_PLAIN);
		ClassPathResource fooResource = new ClassPathResource("org/springframework/http/codec/multipart/foo.txt");
		HttpEntity<ClassPathResource> fooPart = new HttpEntity<>(fooResource, fooHeaders);
		HttpEntity<String> barPart = new HttpEntity<>("bar");
		FormHttpMessageConverter converter = new FormHttpMessageConverter();
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("fooPart", fooPart);
		parts.add("barPart", barPart);
		converter.write(parts, MULTIPART_FORM_DATA, outputMessage);
		byte[] content = outputMessage.getBodyAsBytes();
		return MockServerHttpRequest
				.post("/foo")
				.header(CONTENT_TYPE, outputMessage.getHeaders().getContentType().toString())
				.header(CONTENT_LENGTH, String.valueOf(content.length))
				.body(new String(content));
	}

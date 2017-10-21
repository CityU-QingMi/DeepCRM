	@Test
	public void toFormData() throws Exception {
		BodyExtractor<Mono<MultiValueMap<String, String>>, ServerHttpRequest> extractor = BodyExtractors.toFormData();

		DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
		DefaultDataBuffer dataBuffer =
				factory.wrap(ByteBuffer.wrap("name+1=value+1&name+2=value+2%2B1&name+2=value+2%2B2&name+3".getBytes(StandardCharsets.UTF_8)));
		Flux<DataBuffer> body = Flux.just(dataBuffer);

		MockServerHttpRequest request = MockServerHttpRequest.post("/")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(body);

		Mono<MultiValueMap<String, String>> result = extractor.extract(request, this.context);

		StepVerifier.create(result)
				.consumeNextWith(form -> {
					assertEquals("Invalid result", 3, form.size());
					assertEquals("Invalid result", "value 1", form.getFirst("name 1"));
					List<String> values = form.get("name 2");
					assertEquals("Invalid result", 2, values.size());
					assertEquals("Invalid result", "value 2+1", values.get(0));
					assertEquals("Invalid result", "value 2+2", values.get(1));
					assertNull("Invalid result", form.getFirst("name 3"));
				})
				.expectComplete()
				.verify();
	}

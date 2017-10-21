	@Test
	public void ofFormData() throws Exception {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.set("name 1", "value 1");
		body.add("name 2", "value 2+1");
		body.add("name 2", "value 2+2");
		body.add("name 3", null);

		BodyInserter<MultiValueMap<String, String>, ClientHttpRequest>
				inserter = BodyInserters.fromFormData(body);

		MockClientHttpRequest request = new MockClientHttpRequest(HttpMethod.GET, URI.create("http://example.com"));
		Mono<Void> result = inserter.insert(request, this.context);
		StepVerifier.create(result).expectComplete().verify();

		StepVerifier.create(request.getBody())
				.consumeNextWith(dataBuffer -> {
					byte[] resultBytes = new byte[dataBuffer.readableByteCount()];
					dataBuffer.read(resultBytes);
					assertArrayEquals("name+1=value+1&name+2=value+2%2B1&name+2=value+2%2B2&name+3".getBytes(StandardCharsets.UTF_8),
							resultBytes);
				})
				.expectComplete()
				.verify();

	}

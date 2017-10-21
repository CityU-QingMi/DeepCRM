	@Test
	public void multipartData() {
		Mono<ClientResponse> result = webClient
				.post()
				.uri("http://localhost:" + this.port + "/multipartData")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(generateBody()))
				.exchange();

		StepVerifier
				.create(result)
				.consumeNextWith(response -> assertEquals(HttpStatus.OK, response.statusCode()))
				.verifyComplete();
	}

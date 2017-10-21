	@Test
	public void modelAttribute() {
		Mono<String> result = webClient
				.post()
				.uri("/modelAttribute")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(generateBody()))
				.retrieve()
				.bodyToMono(String.class);

		StepVerifier.create(result)
				.consumeNextWith(body -> assertEquals(
						"FormBean[fieldValue,[fileParts:foo.txt,fileParts:logo.png]]", body))
				.verifyComplete();
	}

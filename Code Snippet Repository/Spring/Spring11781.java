	@Test
	public void requestBodyMap() {
		Mono<String> result = webClient
				.post()
				.uri("/requestBodyMap")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(generateBody()))
				.retrieve()
				.bodyToMono(String.class);

		StepVerifier.create(result)
				.consumeNextWith(body -> assertEquals(
						"Map[[fieldPart],[fileParts:foo.txt,fileParts:logo.png],[jsonPart]]", body))
				.verifyComplete();
	}

	@Test
	public void requestBodyFlux() {
		Mono<String> result = webClient
				.post()
				.uri("/requestBodyFlux")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(generateBody()))
				.retrieve()
				.bodyToMono(String.class);

		StepVerifier.create(result)
				.consumeNextWith(body -> assertEquals(
						"[fieldPart,fileParts:foo.txt,fileParts:logo.png,jsonPart]", body))
				.verifyComplete();
	}

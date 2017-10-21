	@Test
	public void shouldReceiveEmptyResponse() throws Exception {
		prepareResponse(response -> response.setHeader("Content-Length", "0").setBody(""));

		Mono<ResponseEntity<Void>> result = this.webClient.get()
				.uri("/noContent")
				.exchange()
				.flatMap(response -> response.toEntity(Void.class));

		StepVerifier.create(result).assertNext(r -> {
			assertTrue(r.getStatusCode().is2xxSuccessful());
		}).verifyComplete();
	}

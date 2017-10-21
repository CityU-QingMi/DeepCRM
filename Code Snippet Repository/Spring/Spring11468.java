	@Test
	public void shouldReceiveNotFoundEntity() throws Exception {
		prepareResponse(response -> response.setResponseCode(404)
				.setHeader("Content-Type", "text/plain").setBody("Not Found"));

		Mono<ResponseEntity<String>> result = this.webClient.get()
				.uri("/greeting?name=Spring")
				.exchange()
				.flatMap(response -> response.toEntity(String.class));

		StepVerifier.create(result)
				.consumeNextWith(response -> assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()))
				.expectComplete()
				.verify(Duration.ofSeconds(3));

		expectRequestCount(1);
		expectRequest(request -> {
			assertEquals("*/*", request.getHeader(HttpHeaders.ACCEPT));
			assertEquals("/greeting?name=Spring", request.getPath());
		});
	}

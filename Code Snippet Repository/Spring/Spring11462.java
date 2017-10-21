	@Test
	public void shouldReceive404Response() throws Exception {
		prepareResponse(response -> response.setResponseCode(404)
				.setHeader("Content-Type", "text/plain").setBody("Not Found"));

		Mono<ClientResponse> result = this.webClient.get().uri("/greeting?name=Spring").exchange();

		StepVerifier.create(result)
				.consumeNextWith(response -> assertEquals(HttpStatus.NOT_FOUND, response.statusCode()))
				.expectComplete()
				.verify(Duration.ofSeconds(3));

		expectRequestCount(1);
		expectRequest(request -> {
			assertEquals("*/*", request.getHeader(HttpHeaders.ACCEPT));
			assertEquals("/greeting?name=Spring", request.getPath());
		});
	}

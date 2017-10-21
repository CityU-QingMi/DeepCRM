	@Test
	public void shouldGetInternalServerErrorSignal() throws Exception {
		String errorMessage = "Internal Server error";
		prepareResponse(response -> response.setResponseCode(500)
				.setHeader("Content-Type", "text/plain").setBody(errorMessage));

		Mono<String> result = this.webClient.get()
				.uri("/greeting?name=Spring")
				.retrieve()
				.bodyToMono(String.class);

		StepVerifier.create(result)
				.expectErrorSatisfies(throwable -> {
					assertTrue(throwable instanceof WebClientResponseException);
					WebClientResponseException ex = (WebClientResponseException) throwable;
					assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
					assertEquals(MediaType.TEXT_PLAIN, ex.getHeaders().getContentType());
					assertEquals(errorMessage, ex.getResponseBodyAsString());
				})
				.verify(Duration.ofSeconds(3));

		expectRequestCount(1);
		expectRequest(request -> {
			assertEquals("*/*", request.getHeader(HttpHeaders.ACCEPT));
			assertEquals("/greeting?name=Spring", request.getPath());
		});
	}

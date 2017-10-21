	@Test
	public void fixedLocale() {
		Mono<ClientResponse> result = webClient
				.get()
				.uri("http://localhost:" + this.port + "/")
				.exchange();

		StepVerifier
				.create(result)
				.consumeNextWith(response -> {
					assertEquals(HttpStatus.OK, response.statusCode());
					assertEquals(Locale.GERMANY, response.headers().asHttpHeaders().getContentLanguage());
				})
				.verifyComplete();
	}

	@Test
	public void temporaryRedirect() throws Exception {
		URI location = URI.create("http://example.com");
		Mono<ServerResponse> result = ServerResponse.temporaryRedirect(location).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> HttpStatus.TEMPORARY_REDIRECT.equals(response.statusCode()) &&
						location.equals(response.headers().getLocation()))
				.expectComplete()
				.verify();
	}

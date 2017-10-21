	@Test
	public void permanentRedirect() throws Exception {
		URI location = URI.create("http://example.com");
		Mono<ServerResponse> result = ServerResponse.permanentRedirect(location).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> HttpStatus.PERMANENT_REDIRECT.equals(response.statusCode()) &&
						location.equals(response.headers().getLocation()))
				.expectComplete()
				.verify();
	}

	@Test
	public void seeOther() throws Exception {
		URI location = URI.create("http://example.com");
		Mono<ServerResponse> result = ServerResponse.seeOther(location).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> HttpStatus.SEE_OTHER.equals(response.statusCode()) &&
						location.equals(response.headers().getLocation()))
				.expectComplete()
				.verify();
	}

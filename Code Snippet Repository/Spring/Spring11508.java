	@Test
	public void created() throws Exception {
		URI location = URI.create("http://example.com");
		Mono<ServerResponse> result = ServerResponse.created(location).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> HttpStatus.CREATED.equals(response.statusCode()) &&
						location.equals(response.headers().getLocation()))
				.expectComplete()
				.verify();
	}

	@Test
	public void statusCode() throws Exception {
		HttpStatus statusCode = HttpStatus.ACCEPTED;
		Mono<ServerResponse> result = ServerResponse.status(statusCode).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> statusCode.equals(response.statusCode()))
				.expectComplete()
				.verify();

	}

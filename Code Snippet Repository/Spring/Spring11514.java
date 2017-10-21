	@Test
	public void varyBy() throws Exception {
		Mono<ServerResponse> result = ServerResponse.ok().varyBy("foo").build();
		List<String> expected = Collections.singletonList("foo");
		StepVerifier.create(result)
				.expectNextMatches(response -> expected.equals(response.headers().getVary()))
				.expectComplete()
				.verify();

	}

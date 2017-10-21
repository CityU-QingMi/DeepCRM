	@Test
	public void allow() throws Exception {
		Mono<ServerResponse> result = ServerResponse.ok().allow(HttpMethod.GET).build();
		Set<HttpMethod> expected = EnumSet.of(HttpMethod.GET);
		StepVerifier.create(result)
				.expectNextMatches(response -> expected.equals(response.headers().getAllow()))
				.expectComplete()
				.verify();

	}

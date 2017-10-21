	@Test
	public void headers() throws Exception {
		HttpHeaders newHeaders = new HttpHeaders();
		newHeaders.set("foo", "bar");
		Mono<ServerResponse> result =
				ServerResponse.ok().headers(headers -> headers.addAll(newHeaders)).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> newHeaders.equals(response.headers()))
				.expectComplete()
				.verify();

	}

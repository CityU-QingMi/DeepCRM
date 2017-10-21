	@Test
	public void headers() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		Mono<RenderingResponse> result = RenderingResponse.create("foo").headers(headers).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> headers.equals(response.headers()))
				.expectComplete()
				.verify();

	}

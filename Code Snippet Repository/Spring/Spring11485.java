	@Test
	public void headers() throws Exception {
		String body = "foo";
		HttpHeaders headers = new HttpHeaders();
		Mono<EntityResponse<String>> result = EntityResponse.fromObject(body).headers(headers).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> headers.equals(response.headers()))
				.expectComplete()
				.verify();
	}

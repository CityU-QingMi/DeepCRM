	@Test
	public void allow() throws Exception {
		String body = "foo";
		Mono<EntityResponse<String>> result = EntityResponse.fromObject(body).allow(HttpMethod.GET).build();
		Set<HttpMethod> expected = EnumSet.of(HttpMethod.GET);
		StepVerifier.create(result)
				.expectNextMatches(response -> expected.equals(response.headers().getAllow()))
				.expectComplete()
				.verify();
	}

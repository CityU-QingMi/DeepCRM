	@Test
	public void varyBy() throws Exception {
		String body = "foo";
		Mono<EntityResponse<String>> result = EntityResponse.fromObject(body).varyBy("foo").build();
		List<String> expected = Collections.singletonList("foo");
		StepVerifier.create(result)
				.expectNextMatches(response -> expected.equals(response.headers().getVary()))
				.expectComplete()
				.verify();
	}

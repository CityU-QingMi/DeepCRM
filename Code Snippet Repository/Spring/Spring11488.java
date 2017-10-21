	@Test
	public void modelAttributes() throws Exception {
		Map<String, String> model = Collections.singletonMap("foo", "bar");
		Mono<RenderingResponse> result = RenderingResponse.create("foo")
				.modelAttributes(model).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> "bar".equals(response.model().get("foo")))
				.expectComplete()
				.verify();
	}

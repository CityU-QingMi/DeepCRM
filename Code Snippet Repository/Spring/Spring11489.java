	@Test
	public void modelAttributesConventions() throws Exception {
		Set<String> model = Collections.singleton("bar");
		Mono<RenderingResponse> result = RenderingResponse.create("foo")
				.modelAttributes(model).build();
		StepVerifier.create(result)
				.expectNextMatches(response -> "bar".equals(response.model().get("string")))
				.expectComplete()
				.verify();
	}

	@Test
	public void emptyBodyWithRxJava2Maybe() throws Exception {
		ResolvableType type = httpEntityType(Maybe.class, String.class);
		HttpEntity<Maybe<String>> entity = resolveValueWithEmptyBody(type);

		StepVerifier.create(entity.getBody().toFlowable())
				.expectNextCount(0)
				.expectComplete()
				.verify();
	}

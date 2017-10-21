	@Test
	public void emptyBodyWithFlowable() throws Exception {
		ResolvableType type = httpEntityType(Flowable.class, String.class);
		HttpEntity<Flowable<String>> entity = resolveValueWithEmptyBody(type);

		StepVerifier.create(entity.getBody())
				.expectNextCount(0)
				.expectComplete()
				.verify();
	}

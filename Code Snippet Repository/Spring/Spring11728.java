	@Test
	public void emptyBodyWithRxJava2Single() throws Exception {
		ResolvableType type = httpEntityType(io.reactivex.Single.class, String.class);
		HttpEntity<io.reactivex.Single<String>> entity = resolveValueWithEmptyBody(type);

		StepVerifier.create(entity.getBody().toFlowable())
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();
	}

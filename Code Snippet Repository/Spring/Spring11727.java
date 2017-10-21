	@Test
	public void emptyBodyWithSingle() throws Exception {
		ResolvableType type = httpEntityType(Single.class, String.class);
		HttpEntity<Single<String>> entity = resolveValueWithEmptyBody(type);

		StepVerifier.create(RxReactiveStreams.toPublisher(entity.getBody()))
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();
	}

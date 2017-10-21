	@Test
	public void emptyBodyWithRxJava2Observable() throws Exception {
		ResolvableType type = httpEntityType(io.reactivex.Observable.class, String.class);
		HttpEntity<io.reactivex.Observable<String>> entity = resolveValueWithEmptyBody(type);

		StepVerifier.create(entity.getBody().toFlowable(BackpressureStrategy.BUFFER))
				.expectNextCount(0)
				.expectComplete()
				.verify();
	}

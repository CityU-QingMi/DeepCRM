	@Test
	public void emptyBodyWithObservable() throws Exception {
		ResolvableType type = httpEntityType(Observable.class, String.class);
		HttpEntity<Observable<String>> entity = resolveValueWithEmptyBody(type);

		StepVerifier.create(RxReactiveStreams.toPublisher(entity.getBody()))
				.expectNextCount(0)
				.expectComplete()
				.verify();
	}

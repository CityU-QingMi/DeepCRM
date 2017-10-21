	@Test
	public void emptyBodyWithObservable() throws Exception {

		MethodParameter param = this.testMethod.annot(requestBody()).arg(Observable.class, String.class);
		Observable<String> observable = resolveValueWithEmptyBody(param);
		StepVerifier.create(RxReactiveStreams.toPublisher(observable))
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();

		param = this.testMethod.annot(requestBody().notRequired()).arg(Observable.class, String.class);
		observable = resolveValueWithEmptyBody(param);
		StepVerifier.create(RxReactiveStreams.toPublisher(observable))
				.expectNextCount(0)
				.expectComplete()
				.verify();
	}

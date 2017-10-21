	@Test
	public void emptyBodyWithSingle() throws Exception {

		MethodParameter param = this.testMethod.annot(requestBody()).arg(Single.class, String.class);
		Single<String> single = resolveValueWithEmptyBody(param);
		StepVerifier.create(RxReactiveStreams.toPublisher(single))
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();

		param = this.testMethod.annot(requestBody().notRequired()).arg(Single.class, String.class);
		single = resolveValueWithEmptyBody(param);
		StepVerifier.create(RxReactiveStreams.toPublisher(single))
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();
	}

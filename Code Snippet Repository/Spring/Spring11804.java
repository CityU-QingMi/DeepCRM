	@Test
	public void emptyBodyWithMaybe() throws Exception {

		MethodParameter param = this.testMethod.annot(requestBody()).arg(Maybe.class, String.class);
		Maybe<String> maybe = resolveValueWithEmptyBody(param);
		StepVerifier.create(maybe.toFlowable())
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();

		param = this.testMethod.annot(requestBody().notRequired()).arg(Maybe.class, String.class);
		maybe = resolveValueWithEmptyBody(param);
		StepVerifier.create(maybe.toFlowable())
				.expectNextCount(0)
				.expectComplete()
				.verify();
	}

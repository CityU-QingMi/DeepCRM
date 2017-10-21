	@Test
	@SuppressWarnings("")
	public void emptyBodyWithMono() throws Exception {

		MethodParameter param = this.testMethod.annot(requestBody()).arg(Mono.class, String.class);
		StepVerifier.create((Mono<Void>) resolveValueWithEmptyBody(param))
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();

		param = this.testMethod.annot(requestBody().notRequired()).arg(Mono.class, String.class);
		StepVerifier.create((Mono<Void>) resolveValueWithEmptyBody(param))
				.expectNextCount(0)
				.expectComplete()
				.verify();
	}

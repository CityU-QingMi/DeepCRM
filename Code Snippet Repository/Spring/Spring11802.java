	@Test
	@SuppressWarnings("")
	public void emptyBodyWithFlux() throws Exception {

		MethodParameter param = this.testMethod.annot(requestBody()).arg(Flux.class, String.class);
		StepVerifier.create((Flux<Void>) resolveValueWithEmptyBody(param))
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();

		param = this.testMethod.annot(requestBody().notRequired()).arg(Flux.class, String.class);
		StepVerifier.create((Flux<Void>) resolveValueWithEmptyBody(param))
				.expectNextCount(0)
				.expectComplete()
				.verify();
	}

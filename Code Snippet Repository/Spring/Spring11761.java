	private void testValidationError(MethodParameter param, Function<Mono<?>, Mono<?>> valueMonoExtractor)
			throws URISyntaxException {

		ServerWebExchange exchange = postForm("age=invalid");
		Mono<?> mono = createResolver().resolveArgument(param, this.bindContext, exchange);
		mono = valueMonoExtractor.apply(mono);

		StepVerifier.create(mono)
				.consumeErrorWith(ex -> {
					assertTrue(ex instanceof WebExchangeBindException);
					WebExchangeBindException bindException = (WebExchangeBindException) ex;
					assertEquals(1, bindException.getErrorCount());
					assertTrue(bindException.hasFieldErrors("age"));
				})
				.verify();
	}

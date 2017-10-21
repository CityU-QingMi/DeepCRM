	@Test
	public void wrapEmptyWithOptional() throws Exception {
		BindingContext bindingContext = new BindingContext();
		Mono<Object> mono = this.resolver.resolveArgument(this.paramOptional, bindingContext, this.exchange);

		StepVerifier.create(mono)
				.consumeNextWith(value -> {
					assertTrue(value instanceof Optional);
					assertFalse(((Optional<?>) value).isPresent());
				})
				.expectComplete()
				.verify();
	}

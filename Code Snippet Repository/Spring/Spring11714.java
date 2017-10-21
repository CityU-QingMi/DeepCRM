	private void testResolve(Object bindingResult) {

		String key = BindingResult.MODEL_KEY_PREFIX + "foo";
		this.bindingContext.getModel().asMap().put(key, bindingResult);

		MethodParameter parameter = this.testMethod.arg(Errors.class);

		Object actual = this.resolver.resolveArgument(parameter, this.bindingContext, this.exchange)
				.block(Duration.ofMillis(5000));

		assertSame(this.bindingResult, actual);
	}

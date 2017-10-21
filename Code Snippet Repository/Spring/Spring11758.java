	@Test
	public void supports() throws Exception {
		ModelAttributeMethodArgumentResolver resolver =
				new ModelAttributeMethodArgumentResolver(new ReactiveAdapterRegistry(), false);

		MethodParameter param = this.testMethod.annotPresent(ModelAttribute.class).arg(Foo.class);
		assertTrue(resolver.supportsParameter(param));

		param = this.testMethod.annotPresent(ModelAttribute.class).arg(Mono.class, Foo.class);
		assertTrue(resolver.supportsParameter(param));

		param = this.testMethod.annotNotPresent(ModelAttribute.class).arg(Foo.class);
		assertFalse(resolver.supportsParameter(param));

		param = this.testMethod.annotNotPresent(ModelAttribute.class).arg(Mono.class, Foo.class);
		assertFalse(resolver.supportsParameter(param));
	}

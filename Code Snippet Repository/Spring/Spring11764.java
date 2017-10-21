	@Test
	public void supportsWithDefaultResolution() throws Exception {
		ModelAttributeMethodArgumentResolver resolver =
				new ModelAttributeMethodArgumentResolver(new ReactiveAdapterRegistry(), true);

		MethodParameter param = this.testMethod.annotNotPresent(ModelAttribute.class).arg(Foo.class);
		assertTrue(resolver.supportsParameter(param));

		param = this.testMethod.annotNotPresent(ModelAttribute.class).arg(Mono.class, Foo.class);
		assertTrue(resolver.supportsParameter(param));

		param = this.testMethod.annotNotPresent(ModelAttribute.class).arg(String.class);
		assertFalse(resolver.supportsParameter(param));

		param = this.testMethod.annotNotPresent(ModelAttribute.class).arg(Mono.class, String.class);
		assertFalse(resolver.supportsParameter(param));
	}

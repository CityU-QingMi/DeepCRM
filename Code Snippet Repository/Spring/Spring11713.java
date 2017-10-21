	@Test
	public void doesNotSupport() throws Exception {

		MethodParameter parameter = this.testMethod.arg(String.class);
		assertFalse(this.resolver.supportsParameter(parameter));

		try {
			parameter = this.testMethod.arg(ResolvableType.forClassWithGenerics(Mono.class, Errors.class));
			assertFalse(this.resolver.supportsParameter(parameter));
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"ErrorsMethodArgumentResolver doesn't support reactive type wrapper"));
		}
	}

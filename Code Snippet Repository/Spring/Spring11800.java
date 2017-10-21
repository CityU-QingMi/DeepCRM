	@Test
	public void supports() throws Exception {
		MethodParameter param;

		param = this.testMethod.annot(requestBody()).arg(Mono.class, String.class);
		assertTrue(this.resolver.supportsParameter(param));

		param = this.testMethod.annotNotPresent(RequestBody.class).arg(String.class);
		assertFalse(this.resolver.supportsParameter(param));
	}

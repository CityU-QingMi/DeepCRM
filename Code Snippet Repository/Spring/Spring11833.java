	@Test
	public void supportsParameter() {

		MethodParameter param = this.testMethod.annot(requestParam().notRequired("bar")).arg(String.class);
		assertTrue(this.resolver.supportsParameter(param));

		param = this.testMethod.annotPresent(RequestParam.class).arg(String[].class);
		assertTrue(this.resolver.supportsParameter(param));

		param = this.testMethod.annot(requestParam().name("name")).arg(Map.class);
		assertTrue(this.resolver.supportsParameter(param));

		param = this.testMethod.annot(requestParam().name("")).arg(Map.class);
		assertFalse(this.resolver.supportsParameter(param));

		param = this.testMethod.annotNotPresent(RequestParam.class).arg(String.class);
		assertTrue(this.resolver.supportsParameter(param));

		param = this.testMethod.annot(requestParam()).arg(String.class);
		assertTrue(this.resolver.supportsParameter(param));

		param = this.testMethod.annot(requestParam().notRequired()).arg(String.class);
		assertTrue(this.resolver.supportsParameter(param));

	}

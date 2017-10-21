	@Test
	public void supportsParameter() {
		MethodParameter param = this.testMethod.annot(requestParam().noName()).arg(Map.class);
		assertTrue(resolver.supportsParameter(param));

		param = this.testMethod.annotPresent(RequestParam.class).arg(MultiValueMap.class);
		assertTrue(resolver.supportsParameter(param));

		param = this.testMethod.annot(requestParam().name("name")).arg(Map.class);
		assertFalse(resolver.supportsParameter(param));

		param = this.testMethod.annotNotPresent(RequestParam.class).arg(Map.class);
		assertFalse(resolver.supportsParameter(param));
	}

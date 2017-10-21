	@Test
	public void supportsParameter() {
		MethodParameter param = this.testMethod.annot(requestParam().name("")).arg(Map.class);
		assertTrue(this.resolver.supportsParameter(param));

		param = this.testMethod.annotPresent(RequestParam.class).arg(MultiValueMap.class);
		assertTrue(this.resolver.supportsParameter(param));

		param = this.testMethod.annot(requestParam().name("name")).arg(Map.class);
		assertFalse(this.resolver.supportsParameter(param));

		param = this.testMethod.annotNotPresent(RequestParam.class).arg(Map.class);
		assertFalse(this.resolver.supportsParameter(param));

		try {
			param = this.testMethod.annot(requestParam()).arg(Mono.class, Map.class);
			this.resolver.supportsParameter(param);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"RequestParamMapMethodArgumentResolver doesn't support reactive type wrapper"));
		}
	}

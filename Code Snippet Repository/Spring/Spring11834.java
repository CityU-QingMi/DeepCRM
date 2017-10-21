	@Test
	public void doesNotSupportReactiveWrapper() {
		MethodParameter param;
		try {
			param = this.testMethod.annot(requestParam()).arg(Mono.class, String.class);
			this.resolver.supportsParameter(param);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"RequestParamMethodArgumentResolver doesn't support reactive type wrapper"));
		}
		try {
			param = this.testMethod.annotNotPresent(RequestParam.class).arg(Mono.class, String.class);
			this.resolver.supportsParameter(param);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"RequestParamMethodArgumentResolver doesn't support reactive type wrapper"));
		}
	}

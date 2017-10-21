	@Test
	public void testResolveInvalidSignature() throws Exception {
		try {
			BeanUtils.resolveSignature("doSomething(", MethodSignatureBean.class);
			fail("Should not be able to parse with opening but no closing paren.");
		}
		catch (IllegalArgumentException ex) {
			// success
		}

		try {
			BeanUtils.resolveSignature("doSomething)", MethodSignatureBean.class);
			fail("Should not be able to parse with closing but no opening paren.");
		}
		catch (IllegalArgumentException ex) {
			// success
		}
	}

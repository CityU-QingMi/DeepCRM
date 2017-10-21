	@Test
	public void testWithUnsupportedPointcutPrimitive() throws Exception {
		String expression = "call(int org.springframework.tests.sample.beans.TestBean.getAge())";

		try {
			getPointcut(expression).getClassFilter(); // call to getClassFilter forces resolution...
			fail("Should not support call pointcuts");
		}
		catch (UnsupportedPointcutPrimitiveException ex) {
			assertEquals("Should not support call pointcut", PointcutPrimitive.CALL, ex.getUnsupportedPrimitive());
		}

	}

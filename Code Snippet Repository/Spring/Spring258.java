	@Test
	public void testInvalidExpression() {
		String expression = "execution(void org.springframework.tests.sample.beans.TestBean.setSomeNumber(Number) && args(Double)";

		try {
			getPointcut(expression).getClassFilter();  // call to getClassFilter forces resolution
			fail("Invalid expression should throw IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
	}

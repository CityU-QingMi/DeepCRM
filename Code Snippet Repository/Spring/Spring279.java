	@Test
	public void testMatchAnnotationOnClassWithSubpackageWildcard() throws SecurityException, NoSuchMethodException {
		String expression = "within(@(test.annotation..*) *)";
		AspectJExpressionPointcut springAnnotatedPc = testMatchAnnotationOnClass(expression);
		assertFalse(springAnnotatedPc.matches(TestBean.class.getMethod("setName", String.class), TestBean.class));
		assertTrue(springAnnotatedPc.matches(SpringAnnotated.class.getMethod("foo"), SpringAnnotated.class));

		expression = "within(@(test.annotation.transaction..*) *)";
		AspectJExpressionPointcut springTxAnnotatedPc = testMatchAnnotationOnClass(expression);
		assertFalse(springTxAnnotatedPc.matches(SpringAnnotated.class.getMethod("foo"), SpringAnnotated.class));
	}

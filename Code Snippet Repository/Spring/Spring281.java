	@Test
	public void testAnnotationOnMethodWithFQN() throws SecurityException, NoSuchMethodException {
		String expression = "@annotation(test.annotation.transaction.Tx)";
		AspectJExpressionPointcut ajexp = new AspectJExpressionPointcut();
		ajexp.setExpression(expression);

		assertFalse(ajexp.matches(getAge, TestBean.class));
		assertFalse(ajexp.matches(HasTransactionalAnnotation.class.getMethod("foo"), HasTransactionalAnnotation.class));
		assertFalse(ajexp.matches(HasTransactionalAnnotation.class.getMethod("bar", String.class), HasTransactionalAnnotation.class));
		assertFalse(ajexp.matches(BeanA.class.getMethod("setName", String.class), BeanA.class));
		assertTrue(ajexp.matches(BeanA.class.getMethod("getAge"), BeanA.class));
		assertFalse(ajexp.matches(BeanA.class.getMethod("setName", String.class), BeanA.class));
	}

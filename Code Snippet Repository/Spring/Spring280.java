	private AspectJExpressionPointcut testMatchAnnotationOnClass(String expression) throws SecurityException, NoSuchMethodException {
		AspectJExpressionPointcut ajexp = new AspectJExpressionPointcut();
		ajexp.setExpression(expression);

		assertFalse(ajexp.matches(getAge, TestBean.class));
		assertTrue(ajexp.matches(HasTransactionalAnnotation.class.getMethod("foo"), HasTransactionalAnnotation.class));
		assertTrue(ajexp.matches(HasTransactionalAnnotation.class.getMethod("bar", String.class), HasTransactionalAnnotation.class));
		assertTrue(ajexp.matches(BeanB.class.getMethod("setName", String.class), BeanB.class));
		assertFalse(ajexp.matches(BeanA.class.getMethod("setName", String.class), BeanA.class));
		return ajexp;
	}

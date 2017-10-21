	@Test
	public void testAnnotationOnMethodWithWildcard() throws SecurityException, NoSuchMethodException {
		String expression = "execution(@(test.annotation..*) * *(..))";
		AspectJExpressionPointcut anySpringMethodAnnotation = new AspectJExpressionPointcut();
		anySpringMethodAnnotation.setExpression(expression);

		assertFalse(anySpringMethodAnnotation.matches(getAge, TestBean.class));
		assertFalse(anySpringMethodAnnotation.matches(HasTransactionalAnnotation.class.getMethod("foo"), HasTransactionalAnnotation.class));
		assertFalse(anySpringMethodAnnotation.matches(HasTransactionalAnnotation.class.getMethod("bar", String.class), HasTransactionalAnnotation.class));
		assertFalse(anySpringMethodAnnotation.matches(BeanA.class.getMethod("setName", String.class), BeanA.class));
		assertTrue(anySpringMethodAnnotation.matches(BeanA.class.getMethod("getAge"), BeanA.class));
		assertFalse(anySpringMethodAnnotation.matches(BeanA.class.getMethod("setName", String.class), BeanA.class));
	}

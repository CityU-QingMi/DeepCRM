	@Test
	public void testAnnotationOnMethodArgumentsWithFQN() throws SecurityException, NoSuchMethodException {
		String expression = "@args(*, test.annotation.EmptySpringAnnotation))";
		AspectJExpressionPointcut takesSpringAnnotatedArgument2 = new AspectJExpressionPointcut();
		takesSpringAnnotatedArgument2.setExpression(expression);

		assertFalse(takesSpringAnnotatedArgument2.matches(getAge, TestBean.class));
		assertFalse(takesSpringAnnotatedArgument2.matches(HasTransactionalAnnotation.class.getMethod("foo"), HasTransactionalAnnotation.class));
		assertFalse(takesSpringAnnotatedArgument2.matches(HasTransactionalAnnotation.class.getMethod("bar", String.class), HasTransactionalAnnotation.class));
		assertFalse(takesSpringAnnotatedArgument2.matches(BeanA.class.getMethod("setName", String.class), BeanA.class));
		assertFalse(takesSpringAnnotatedArgument2.matches(BeanA.class.getMethod("getAge"), BeanA.class));
		assertFalse(takesSpringAnnotatedArgument2.matches(BeanA.class.getMethod("setName", String.class), BeanA.class));

		assertTrue(takesSpringAnnotatedArgument2.matches(
				ProcessesSpringAnnotatedParameters.class.getMethod("takesAnnotatedParameters", TestBean.class, SpringAnnotated.class),
				ProcessesSpringAnnotatedParameters.class));

		// True because it maybeMatches with potential argument subtypes
		assertTrue(takesSpringAnnotatedArgument2.matches(
				ProcessesSpringAnnotatedParameters.class.getMethod("takesNoAnnotatedParameters", TestBean.class, BeanA.class),
				ProcessesSpringAnnotatedParameters.class));

		assertFalse(takesSpringAnnotatedArgument2.matches(
				ProcessesSpringAnnotatedParameters.class.getMethod("takesNoAnnotatedParameters", TestBean.class, BeanA.class),
				ProcessesSpringAnnotatedParameters.class, new TestBean(), new BeanA())
		);
	}

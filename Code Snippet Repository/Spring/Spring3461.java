	@Test
	public void factoryBeanAccess() { // SPR9511
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(new SimpleBeanResolver());
		Expression expr = new SpelExpressionParser().parseRaw("@car.colour");
		assertEquals("red", expr.getValue(context));
		expr = new SpelExpressionParser().parseRaw("&car.class.name");
		assertEquals(CarFactoryBean.class.getName(), expr.getValue(context));

		expr = new SpelExpressionParser().parseRaw("@boat.colour");
		assertEquals("blue",expr.getValue(context));
		expr = new SpelExpressionParser().parseRaw("&boat.class.name");
		try {
			assertEquals(Boat.class.getName(), expr.getValue(context));
			fail("Expected BeanIsNotAFactoryException");
		}
		catch (BeanIsNotAFactoryException binafe) {
			// success
		}

		// No such bean
		try {
			expr = new SpelExpressionParser().parseRaw("@truck");
			assertEquals("red", expr.getValue(context));
			fail("Expected NoSuchBeanDefinitionException");
		}
		catch (NoSuchBeanDefinitionException nsbde) {
			// success
		}

		// No such factory bean
		try {
			expr = new SpelExpressionParser().parseRaw("&truck");
			assertEquals(CarFactoryBean.class.getName(), expr.getValue(context));
			fail("Expected NoSuchBeanDefinitionException");
		}
		catch (NoSuchBeanDefinitionException nsbde) {
			// success
		}
	}

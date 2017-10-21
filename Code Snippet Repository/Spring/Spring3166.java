	@Test
	public void beanMethodOverridingWithNarrowedReturnType() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(NarrowedOverridingConfig.class);
		ctx.setAllowBeanDefinitionOverriding(false);
		ctx.refresh();
		assertFalse(ctx.getDefaultListableBeanFactory().containsSingleton("testBean"));
		assertEquals("overridden", ctx.getBean("testBean", TestBean.class).toString());
		assertTrue(ctx.getDefaultListableBeanFactory().containsSingleton("testBean"));
	}

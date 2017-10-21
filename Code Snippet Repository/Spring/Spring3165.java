	@Test
	public void beanMethodOverridingOnASM() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.registerBeanDefinition("config", new RootBeanDefinition(OverridingConfig.class.getName()));
		ctx.setAllowBeanDefinitionOverriding(false);
		ctx.refresh();
		assertFalse(ctx.getDefaultListableBeanFactory().containsSingleton("testBean"));
		assertEquals("overridden", ctx.getBean("testBean", TestBean.class).toString());
		assertTrue(ctx.getDefaultListableBeanFactory().containsSingleton("testBean"));
	}

	@Test
	public void beanMethodOverloadingWithInheritance() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(SubConfig.class);
		ctx.setAllowBeanDefinitionOverriding(false);
		ctx.refresh();
		assertFalse(ctx.getDefaultListableBeanFactory().containsSingleton("aString"));
		assertThat(ctx.getBean(String.class), equalTo("overloaded5"));
		assertTrue(ctx.getDefaultListableBeanFactory().containsSingleton("aString"));
	}

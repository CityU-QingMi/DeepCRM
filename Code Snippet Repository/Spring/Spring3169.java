	@Test
	public void beanMethodOverloadingWithAdditionalMetadataButOtherMethodExecuted() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithOverloadingAndAdditionalMetadata.class);
		ctx.getDefaultListableBeanFactory().registerSingleton("anInt", 5);
		ctx.setAllowBeanDefinitionOverriding(false);
		ctx.refresh();
		assertFalse(ctx.getDefaultListableBeanFactory().containsSingleton("aString"));
		assertThat(ctx.getBean(String.class), equalTo("overloaded5"));
		assertTrue(ctx.getDefaultListableBeanFactory().containsSingleton("aString"));
	}

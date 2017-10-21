	@Test
	public void beanMethodOverloadingWithAdditionalMetadata() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithOverloadingAndAdditionalMetadata.class);
		ctx.setAllowBeanDefinitionOverriding(false);
		ctx.refresh();
		assertFalse(ctx.getDefaultListableBeanFactory().containsSingleton("aString"));
		assertThat(ctx.getBean(String.class), equalTo("regular"));
		assertTrue(ctx.getDefaultListableBeanFactory().containsSingleton("aString"));
	}

	@Test
	public void testCustomWithAsm() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.registerBeanDefinition("customConfig", new RootBeanDefinition(CustomConfig.class.getName()));
		RootBeanDefinition customPojo = new RootBeanDefinition(CustomPojo.class.getName());
		customPojo.setLazyInit(true);
		ctx.registerBeanDefinition("customPojo", customPojo);
		ctx.refresh();
		assertFalse(ctx.getBeanFactory().containsSingleton("testBean1"));
		CustomPojo pojo = ctx.getBean(CustomPojo.class);
		assertThat(pojo.testBean.getName(), equalTo("interesting"));
	}

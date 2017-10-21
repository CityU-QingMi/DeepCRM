	@Test
	public void viaBeanRegistration() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("componentScanAnnotatedConfig",
				genericBeanDefinition(ComponentScanAnnotatedConfig.class).getBeanDefinition());
		bf.registerBeanDefinition("configurationClassPostProcessor",
				genericBeanDefinition(ConfigurationClassPostProcessor.class).getBeanDefinition());
		GenericApplicationContext ctx = new GenericApplicationContext(bf);
		ctx.refresh();
		ctx.getBean(ComponentScanAnnotatedConfig.class);
		ctx.getBean(TestBean.class);
		assertThat("config class bean not found", ctx.containsBeanDefinition("componentScanAnnotatedConfig"), is(true));
		assertThat("@ComponentScan annotated @Configuration class registered " +
				"as bean definition did not trigger component scanning as expected",
				ctx.containsBean("fooServiceImpl"), is(true));
	}

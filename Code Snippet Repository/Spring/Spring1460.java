	@Test
	public void testPropertyPlaceholderConfigurerWithPlaceholderInAlias() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class).getBeanDefinition());
		factory.registerAlias("tb", "${alias}");

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.put("alias", "tb2");
		ppc.setProperties(props);
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		TestBean tb2 = (TestBean) factory.getBean("tb2");
		assertSame(tb, tb2);
	}

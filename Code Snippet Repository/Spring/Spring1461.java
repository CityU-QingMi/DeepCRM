	@Test
	public void testPropertyPlaceholderConfigurerWithSelfReferencingPlaceholderInAlias() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class).getBeanDefinition());
		factory.registerAlias("tb", "${alias}");

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.put("alias", "tb");
		ppc.setProperties(props);
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertNotNull(tb);
		assertEquals(0, factory.getAliases("tb").length);
	}

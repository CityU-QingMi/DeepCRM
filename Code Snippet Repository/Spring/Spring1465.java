	@Test
	public void testPropertyPlaceholderConfigurerWithAliases() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("touchy", "${test}").getBeanDefinition());

		factory.registerAlias("tb", "${myAlias}");
		factory.registerAlias("${myTarget}", "alias2");

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.put("test", "mytest");
		props.put("myAlias", "alias");
		props.put("myTarget", "tb");
		ppc.setProperties(props);
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals("mytest", tb.getTouchy());
		tb = (TestBean) factory.getBean("alias");
		assertEquals("mytest", tb.getTouchy());
		tb = (TestBean) factory.getBean("alias2");
		assertEquals("mytest", tb.getTouchy());
	}

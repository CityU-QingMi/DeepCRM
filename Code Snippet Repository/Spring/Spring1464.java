	@Test
	public void testPropertyPlaceholderConfigurerWithInlineDefault() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("touchy", "${test:mytest}").getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals("mytest", tb.getTouchy());
	}

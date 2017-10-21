	@Test
	public void testPropertyPlaceholderConfigurerWithIgnoreUnresolvablePlaceholder() {
		factory.registerBeanDefinition("tb", genericBeanDefinition(TestBean.class)
				.addPropertyValue("name", "${ref}").getBeanDefinition());

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setIgnoreUnresolvablePlaceholders(true);
		ppc.postProcessBeanFactory(factory);

		TestBean tb = (TestBean) factory.getBean("tb");
		assertEquals("${ref}", tb.getName());
	}

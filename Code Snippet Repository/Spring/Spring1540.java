	@Test
	public void testSetBean() throws Exception {
		Assume.group(TestGroup.LONG_RUNNING);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(
				new ClassPathResource("genericBeanTests.xml", getClass()));
		UrlSet us = (UrlSet) bf.getBean("setBean");
		assertEquals(1, us.size());
		assertEquals(new URL("http://www.springframework.org"), us.iterator().next());
	}

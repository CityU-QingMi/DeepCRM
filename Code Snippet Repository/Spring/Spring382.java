	@Test
	public void testScopedProxyReplacesAutowireCandidateTrue() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(SCOPED_AUTOWIRE_TRUE_CONTEXT);
		assertTrue(Arrays.asList(bf.getBeanNamesForType(TestBean.class, true, false)).contains("scoped"));
		assertTrue(Arrays.asList(bf.getBeanNamesForType(TestBean.class, false, false)).contains("scoped"));
		assertFalse(bf.containsSingleton("scoped"));
		TestBean autowired = (TestBean) bf.getBean("autowired");
		TestBean scoped = (TestBean) bf.getBean("scoped");
		assertSame(scoped, autowired.getChild());
	}

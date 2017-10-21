	@Test
	public void testScopedProxyInheritsAutowireCandidateFalse() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(SCOPED_AUTOWIRE_FALSE_CONTEXT);
		assertTrue(Arrays.asList(bf.getBeanNamesForType(TestBean.class, false, false)).contains("scoped"));
		assertTrue(Arrays.asList(bf.getBeanNamesForType(TestBean.class, true, false)).contains("scoped"));
		assertFalse(bf.containsSingleton("scoped"));
		TestBean autowired = (TestBean) bf.getBean("autowired");
		TestBean unscoped = (TestBean) bf.getBean("unscoped");
		assertSame(unscoped, autowired.getChild());
	}

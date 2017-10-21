	@Test
	public void testGlobalsWithoutTarget() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(INVALID_CONTEXT, CLASS));
		try {
			bf.getBean("globalsWithoutTarget");
			fail("Should require target name");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getCause() instanceof AopConfigException);
		}
	}

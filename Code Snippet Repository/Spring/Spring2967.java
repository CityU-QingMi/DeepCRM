	@Test
	public void testAutowireModeNotInherited() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(OVERRIDES_CONTEXT);

		TestBean david = (TestBean) xbf.getBean("magicDavid");
		// the parent bean is autowiring
		assertNotNull(david.getSpouse());

		TestBean derivedDavid = (TestBean) xbf.getBean("magicDavidDerived");
		// this fails while it inherits from the child bean
		assertNull("autowiring not propagated along child relationships", derivedDavid.getSpouse());
	}

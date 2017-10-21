	@Test
	public void testCircularReferenceWithTwoFactoryBeans() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
		reader.loadBeanDefinitions(REFTYPES_CONTEXT);
		TestBean ego1 = (TestBean) xbf.getBean("ego1");
		assertTrue("Correct circular reference", ego1.getSpouse().getSpouse() == ego1);
		TestBean ego3 = (TestBean) xbf.getBean("ego3");
		assertTrue("Correct circular reference", ego3.getSpouse().getSpouse() == ego3);
	}

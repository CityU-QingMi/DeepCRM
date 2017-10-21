	@Test
	public void testCircularReferencesWithWrappingAndRawInjectionAllowed() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		xbf.setAllowRawInjectionDespiteWrapping(true);
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
		reader.loadBeanDefinitions(REFTYPES_CONTEXT);
		xbf.addBeanPostProcessor(new WrappingPostProcessor());

		ITestBean jenny = (ITestBean) xbf.getBean("jenny");
		ITestBean david = (ITestBean) xbf.getBean("david");
		assertTrue(AopUtils.isAopProxy(jenny));
		assertTrue(AopUtils.isAopProxy(david));
		assertSame(david, jenny.getSpouse());
		assertNotSame(jenny, david.getSpouse());
		assertEquals("Jenny", david.getSpouse().getName());
		assertSame(david, david.getSpouse().getSpouse());
		assertTrue(AopUtils.isAopProxy(jenny.getSpouse()));
		assertTrue(!AopUtils.isAopProxy(david.getSpouse()));
	}

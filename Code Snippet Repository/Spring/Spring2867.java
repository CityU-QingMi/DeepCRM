	@Test
	public void testGetObjectTypeWithNoTargetOrTargetSource() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(TARGETSOURCE_CONTEXT, CLASS));

		ITestBean tb = (ITestBean) bf.getBean("noTarget");
		try {
			tb.getName();
			fail();
		}
		catch (UnsupportedOperationException ex) {
			assertEquals("getName", ex.getMessage());
		}
		FactoryBean<?> pfb = (ProxyFactoryBean) bf.getBean("&noTarget");
		assertTrue("Has correct object type", ITestBean.class.isAssignableFrom(pfb.getObjectType()));
	}

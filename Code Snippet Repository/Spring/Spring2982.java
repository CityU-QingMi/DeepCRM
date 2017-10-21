	@Test
	public void testInitMethodThrowsException() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(INITIALIZERS_CONTEXT);
		try {
			xbf.getBean("init-method2");
			fail();
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getResourceDescription().indexOf("initializers.xml") != -1);
			assertEquals("init-method2", ex.getBeanName());
			assertTrue(ex.getCause() instanceof IOException);
		}
	}

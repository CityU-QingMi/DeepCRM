	@Test
	public void testNoSuchInitMethod() throws Exception {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(INITIALIZERS_CONTEXT);
		try {
			xbf.getBean("init-method3");
			fail();
		}
		catch (FatalBeanException ex) {
			// check message is helpful
			assertTrue(ex.getMessage().indexOf("initializers.xml") != -1);
			assertTrue(ex.getMessage().indexOf("init-method3") != -1);
			assertTrue(ex.getMessage().indexOf("init") != -1);
		}
	}

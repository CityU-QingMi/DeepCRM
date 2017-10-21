	@Test
	public void testBogusParentageFromParentFactory() throws Exception {
		DefaultListableBeanFactory parent = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(parent).loadBeanDefinitions(PARENT_CONTEXT);
		DefaultListableBeanFactory child = new DefaultListableBeanFactory(parent);
		new XmlBeanDefinitionReader(child).loadBeanDefinitions(CHILD_CONTEXT);
		try {
			child.getBean("bogusParent", TestBean.class);
			fail();
		}
		catch (BeanDefinitionStoreException ex) {
			// check exception message contains the name
			assertTrue(ex.getMessage().indexOf("bogusParent") != -1);
			assertTrue(ex.getCause() instanceof NoSuchBeanDefinitionException);
		}
	}

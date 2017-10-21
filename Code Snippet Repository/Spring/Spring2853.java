	private void testDoubleTargetSourceIsRejected(String name) {
		try {
			DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
			new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(DBL_TARGETSOURCE_CONTEXT, CLASS));
			bf.getBean(name);
			fail("Should not allow TargetSource to be specified in interceptorNames as well as targetSource property");
		}
		catch (BeanCreationException ex) {
			// Root cause of the problem must be an AOP exception
			AopConfigException aex = (AopConfigException) ex.getCause();
			assertTrue(aex.getMessage().indexOf("TargetSource") != -1);
		}
	}

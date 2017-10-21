	@Test
	public void testTargetSourceNotAtEndOfInterceptorNamesIsRejected() {
		try {
			DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
			new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(NOTLAST_TARGETSOURCE_CONTEXT, CLASS));
			bf.getBean("targetSourceNotLast");
			fail("TargetSource or non-advised object must be last in interceptorNames");
		}
		catch (BeanCreationException ex) {
			// Root cause of the problem must be an AOP exception
			AopConfigException aex = (AopConfigException) ex.getCause();
			assertTrue(aex.getMessage().contains("interceptorNames"));
		}
	}

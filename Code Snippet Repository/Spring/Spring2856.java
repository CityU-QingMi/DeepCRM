	@Test
	public void testEmptyInterceptorNames() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(INVALID_CONTEXT, CLASS));
		try {
			bf.getBean("emptyInterceptorNames");
			fail("Interceptor names cannot be empty");
		}
		catch (BeanCreationException ex) {
			// Ok
		}
	}

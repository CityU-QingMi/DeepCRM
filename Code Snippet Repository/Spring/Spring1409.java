	@Test
	public void testWithCustomAnnotation() {
		try {
			DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
			BeanDefinition beanDef = BeanDefinitionBuilder
				.genericBeanDefinition(RequiredTestBean.class)
				.getBeanDefinition();
			factory.registerBeanDefinition("testBean", beanDef);
			RequiredAnnotationBeanPostProcessor rabpp = new RequiredAnnotationBeanPostProcessor();
			rabpp.setRequiredAnnotationType(MyRequired.class);
			factory.addBeanPostProcessor(rabpp);
			factory.preInstantiateSingletons();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			String message = ex.getCause().getMessage();
			assertTrue(message.contains("Property"));
			assertTrue(message.contains("name"));
			assertTrue(message.contains("testBean"));
		}
	}

	@Test
	public void testWithThreeRequiredPropertiesOmitted() {
		try {
			DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
			BeanDefinition beanDef = BeanDefinitionBuilder
				.genericBeanDefinition(RequiredTestBean.class)
				.addPropertyValue("name", "Rob Harrop")
				.getBeanDefinition();
			factory.registerBeanDefinition("testBean", beanDef);
			factory.addBeanPostProcessor(new RequiredAnnotationBeanPostProcessor());
			factory.preInstantiateSingletons();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			String message = ex.getCause().getMessage();
			assertTrue(message.contains("Properties"));
			assertTrue(message.contains("age"));
			assertTrue(message.contains("favouriteColour"));
			assertTrue(message.contains("jobTitle"));
			assertTrue(message.contains("testBean"));
		}
	}

	@Test
	public void testWithStaticFactoryMethod() {
		try {
			DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
			BeanDefinition beanDef = BeanDefinitionBuilder
					.genericBeanDefinition(RequiredTestBean.class)
					.setFactoryMethod("create")
					.addPropertyValue("name", "Rob Harrop")
					.addPropertyValue("favouriteColour", "Blue")
					.addPropertyValue("jobTitle", "Grand Poobah")
					.getBeanDefinition();
			factory.registerBeanDefinition("testBean", beanDef);
			factory.addBeanPostProcessor(new RequiredAnnotationBeanPostProcessor());
			factory.preInstantiateSingletons();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			String message = ex.getCause().getMessage();
			assertTrue(message.contains("Property"));
			assertTrue(message.contains("age"));
			assertTrue(message.contains("testBean"));
		}
	}

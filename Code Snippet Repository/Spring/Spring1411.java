	@Test
	public void testWithStaticFactoryMethodAndRequiredPropertiesSpecified() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		BeanDefinition beanDef = BeanDefinitionBuilder
				.genericBeanDefinition(RequiredTestBean.class)
				.setFactoryMethod("create")
				.addPropertyValue("age", "24")
				.addPropertyValue("favouriteColour", "Blue")
				.addPropertyValue("jobTitle", "Grand Poobah")
				.getBeanDefinition();
		factory.registerBeanDefinition("testBean", beanDef);
		factory.addBeanPostProcessor(new RequiredAnnotationBeanPostProcessor());
		factory.preInstantiateSingletons();
		RequiredTestBean bean = (RequiredTestBean) factory.getBean("testBean");
		assertEquals(24, bean.getAge());
		assertEquals("Blue", bean.getFavouriteColour());
	}

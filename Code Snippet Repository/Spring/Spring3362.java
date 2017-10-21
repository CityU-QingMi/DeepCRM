	@Test
	public void configWithFactoryBeanReturnType() {
		ListableBeanFactory factory = initBeanFactory(ConfigWithNonSpecificReturnTypes.class);
		assertEquals(List.class, factory.getType("factoryBean"));
		assertTrue(factory.isTypeMatch("factoryBean", List.class));
		assertEquals(FactoryBean.class, factory.getType("&factoryBean"));
		assertTrue(factory.isTypeMatch("&factoryBean", FactoryBean.class));
		assertFalse(factory.isTypeMatch("&factoryBean", BeanClassLoaderAware.class));
		assertFalse(factory.isTypeMatch("&factoryBean", ListFactoryBean.class));
		assertTrue(factory.getBean("factoryBean") instanceof List);

		String[] beanNames = factory.getBeanNamesForType(FactoryBean.class);
		assertEquals(1, beanNames.length);
		assertEquals("&factoryBean", beanNames[0]);

		beanNames = factory.getBeanNamesForType(BeanClassLoaderAware.class);
		assertEquals(1, beanNames.length);
		assertEquals("&factoryBean", beanNames[0]);

		beanNames = factory.getBeanNamesForType(ListFactoryBean.class);
		assertEquals(1, beanNames.length);
		assertEquals("&factoryBean", beanNames[0]);

		beanNames = factory.getBeanNamesForType(List.class);
		assertEquals("factoryBean", beanNames[0]);
	}

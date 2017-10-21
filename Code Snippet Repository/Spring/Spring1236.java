	@Test
	public void testGetBeanNamesForTypeAfterFactoryBeanCreation() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		lbf.registerBeanDefinition("factoryBean", new RootBeanDefinition(FactoryBeanThatShouldntBeCalled.class));
		lbf.getBean("&factoryBean");

		String[] beanNames = lbf.getBeanNamesForType(Runnable.class, false, false);
		assertEquals(1, beanNames.length);
		assertEquals("&factoryBean", beanNames[0]);

		beanNames = lbf.getBeanNamesForType(Callable.class, false, false);
		assertEquals(1, beanNames.length);
		assertEquals("&factoryBean", beanNames[0]);

		beanNames = lbf.getBeanNamesForType(RepositoryFactoryInformation.class, false, false);
		assertEquals(1, beanNames.length);
		assertEquals("&factoryBean", beanNames[0]);

		beanNames = lbf.getBeanNamesForType(FactoryBean.class, false, false);
		assertEquals(1, beanNames.length);
		assertEquals("&factoryBean", beanNames[0]);
	}

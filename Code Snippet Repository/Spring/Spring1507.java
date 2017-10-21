	@Test
	public void testServiceFactoryBean() {
		assumeTrue(ServiceLoader.load(DocumentBuilderFactory.class).iterator().hasNext());

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(ServiceFactoryBean.class);
		bd.getPropertyValues().add("serviceType", DocumentBuilderFactory.class.getName());
		bf.registerBeanDefinition("service", bd);
		assertTrue(bf.getBean("service") instanceof DocumentBuilderFactory);
	}

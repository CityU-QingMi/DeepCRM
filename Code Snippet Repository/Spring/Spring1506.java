	@Test
	public void testServiceLoaderFactoryBean() {
		assumeTrue(ServiceLoader.load(DocumentBuilderFactory.class).iterator().hasNext());

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(ServiceLoaderFactoryBean.class);
		bd.getPropertyValues().add("serviceType", DocumentBuilderFactory.class.getName());
		bf.registerBeanDefinition("service", bd);
		ServiceLoader<?> serviceLoader = (ServiceLoader<?>) bf.getBean("service");
		assertTrue(serviceLoader.iterator().next() instanceof DocumentBuilderFactory);
	}

	@Test
	public void testServiceListFactoryBean() {
		assumeTrue(ServiceLoader.load(DocumentBuilderFactory.class).iterator().hasNext());

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(ServiceListFactoryBean.class);
		bd.getPropertyValues().add("serviceType", DocumentBuilderFactory.class.getName());
		bf.registerBeanDefinition("service", bd);
		List<?> serviceList = (List<?>) bf.getBean("service");
		assertTrue(serviceList.get(0) instanceof DocumentBuilderFactory);
	}

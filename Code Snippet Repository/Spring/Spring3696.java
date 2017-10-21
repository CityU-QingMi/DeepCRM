	@Test
	public void testIgnoreNullObjectFromFactoryBean() throws MalformedObjectNameException {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition("spring:type=FactoryBean", new RootBeanDefinition(NullSomethingFactoryBean.class));

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		exporter.setBeanFactory(factory);
		exporter.setAutodetectMode(MBeanExporter.AUTODETECT_ALL);

		start(exporter);
		assertIsNotRegistered("Null FactoryBean object not registered",
				ObjectNameManager.getInstance("spring:type=FactoryBean"));
	}

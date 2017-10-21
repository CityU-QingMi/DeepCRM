	@Test
	public void testRegisterFactoryBean() throws MalformedObjectNameException {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition("spring:type=FactoryBean", new RootBeanDefinition(ProperSomethingFactoryBean.class));

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		exporter.setBeanFactory(factory);
		exporter.setAutodetectMode(MBeanExporter.AUTODETECT_ALL);

		start(exporter);
		assertIsRegistered("Non-null FactoryBean object registered",
				ObjectNameManager.getInstance("spring:type=FactoryBean"));
	}

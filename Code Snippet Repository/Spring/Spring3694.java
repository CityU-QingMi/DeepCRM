	@Test
	public void testIgnoreBeanName() throws MalformedObjectNameException {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		String firstBeanName = "spring:type=TestBean";
		factory.registerSingleton(firstBeanName, new TestBean("test"));
		String secondBeanName = "spring:type=TestBean2";
		factory.registerSingleton(secondBeanName, new TestBean("test2"));

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		exporter.setAssembler(new NamedBeanAutodetectCapableMBeanInfoAssemblerStub(firstBeanName, secondBeanName));
		exporter.setBeanFactory(factory);
		exporter.setAutodetectMode(MBeanExporter.AUTODETECT_ALL);
		exporter.addExcludedBean(secondBeanName);

		start(exporter);
		assertIsRegistered("Bean not autodetected in (AUTODETECT_ALL) mode",
				ObjectNameManager.getInstance(firstBeanName));
		assertIsNotRegistered("Bean should have been excluded",
				ObjectNameManager.getInstance(secondBeanName));
	}

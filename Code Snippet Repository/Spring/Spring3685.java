	@Test
	public void testOnlyBonaFideMBeanIsExportedWhenAutodetectIsMBeanOnly() throws Exception {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition(OBJECT_NAME, builder.getBeanDefinition());
		String exportedBeanName = "spring:type=TestBean";
		factory.registerSingleton(exportedBeanName, new TestBean());

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		exporter.setAssembler(new NamedBeanAutodetectCapableMBeanInfoAssemblerStub(exportedBeanName));
		exporter.setBeanFactory(factory);
		exporter.setAutodetectMode(MBeanExporter.AUTODETECT_MBEAN);
		start(exporter);

		assertIsRegistered("Bona fide MBean not autodetected in AUTODETECT_MBEAN mode",
				ObjectNameManager.getInstance(OBJECT_NAME));
		assertIsNotRegistered("Bean autodetected and (only) AUTODETECT_MBEAN mode is on",
				ObjectNameManager.getInstance(exportedBeanName));
	}

	@Test
	public void testBonaFideMBeanAndRegularBeanExporterWithAutodetectAll() throws Exception {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition(OBJECT_NAME, builder.getBeanDefinition());
		String exportedBeanName = "spring:type=TestBean";
		factory.registerSingleton(exportedBeanName, new TestBean());
		String notToBeExportedBeanName = "spring:type=NotToBeExported";
		factory.registerSingleton(notToBeExportedBeanName, new TestBean());

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		exporter.setAssembler(new NamedBeanAutodetectCapableMBeanInfoAssemblerStub(exportedBeanName));
		exporter.setBeanFactory(factory);
		exporter.setAutodetectMode(MBeanExporter.AUTODETECT_ALL);
		start(exporter);
		assertIsRegistered("Bona fide MBean not autodetected in (AUTODETECT_ALL) mode",
				ObjectNameManager.getInstance(OBJECT_NAME));
		assertIsRegistered("Bean not autodetected in (AUTODETECT_ALL) mode",
				ObjectNameManager.getInstance(exportedBeanName));
		assertIsNotRegistered("Bean autodetected and did not satisfy the autodetect info assembler",
				ObjectNameManager.getInstance(notToBeExportedBeanName));
	}

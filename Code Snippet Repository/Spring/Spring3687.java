	@Test
	public void testBonaFideMBeanIsNotExportedWithAutodetectAssembler() throws Exception {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition(OBJECT_NAME, builder.getBeanDefinition());
		String exportedBeanName = "spring:type=TestBean";
		factory.registerSingleton(exportedBeanName, new TestBean());

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		exporter.setAssembler(new NamedBeanAutodetectCapableMBeanInfoAssemblerStub(exportedBeanName));
		exporter.setBeanFactory(factory);
		exporter.setAutodetectMode(MBeanExporter.AUTODETECT_ASSEMBLER);
		start(exporter);
		assertIsNotRegistered("Bona fide MBean was autodetected in AUTODETECT_ASSEMBLER mode - must not have been",
				ObjectNameManager.getInstance(OBJECT_NAME));
		assertIsRegistered("Bean not autodetected in AUTODETECT_ASSEMBLER mode",
				ObjectNameManager.getInstance(exportedBeanName));
	}

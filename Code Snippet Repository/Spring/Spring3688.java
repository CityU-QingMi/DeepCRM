	@Test
	public void testBonaFideMBeanExplicitlyExportedAndAutodetectionIsOn() throws Exception {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition(OBJECT_NAME, builder.getBeanDefinition());

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		Map<String, Object> beansToExport = new HashMap<>();
		beansToExport.put(OBJECT_NAME, OBJECT_NAME);
		exporter.setBeans(beansToExport);
		exporter.setAssembler(new NamedBeanAutodetectCapableMBeanInfoAssemblerStub(OBJECT_NAME));
		exporter.setBeanFactory(factory);
		exporter.setAutodetectMode(MBeanExporter.AUTODETECT_ASSEMBLER);
		start(exporter);
		assertIsRegistered("Explicitly exported bona fide MBean obviously not exported.",
				ObjectNameManager.getInstance(OBJECT_NAME));
	}

	@Test
	public void testBonaFideMBeanIsNotExportedWhenAutodetectIsTotallyTurnedOff() throws Exception {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition("^&_invalidObjectName_(*", builder.getBeanDefinition());
		String exportedBeanName = "export.me.please";
		factory.registerSingleton(exportedBeanName, new TestBean());

		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beansToExport = new HashMap<>();
		beansToExport.put(OBJECT_NAME, exportedBeanName);
		exporter.setBeans(beansToExport);
		exporter.setServer(getServer());
		exporter.setBeanFactory(factory);
		exporter.setAutodetectMode(MBeanExporter.AUTODETECT_NONE);
		// MBean has a bad ObjectName, so if said MBean is autodetected, an exception will be thrown...
		start(exporter);

	}

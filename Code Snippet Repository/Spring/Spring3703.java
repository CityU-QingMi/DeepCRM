	@Test
	public void testWithMBeanExporterListeners() throws Exception {
		MockMBeanExporterListener listener1 = new MockMBeanExporterListener();
		MockMBeanExporterListener listener2 = new MockMBeanExporterListener();

		MBeanExporter exporter = new MBeanExporter();
		exporter.setBeans(getBeanMap());
		exporter.setServer(server);
		exporter.setListeners(listener1, listener2);
		start(exporter);
		exporter.destroy();

		assertListener(listener1);
		assertListener(listener2);
	}

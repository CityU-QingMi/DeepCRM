	@Test
	public void testMBeanIsNotUnregisteredSpuriouslyIfSomeExternalProcessHasUnregisteredMBean() throws Exception {
		MBeanExporter exporter = new MBeanExporter();
		exporter.setBeans(getBeanMap());
		exporter.setServer(this.server);
		MockMBeanExporterListener listener = new MockMBeanExporterListener();
		exporter.setListeners(listener);
		start(exporter);
		assertIsRegistered("The bean was not registered with the MBeanServer",
				ObjectNameManager.getInstance(OBJECT_NAME));

		this.server.unregisterMBean(new ObjectName(OBJECT_NAME));
		exporter.destroy();
		assertEquals("Listener should not have been invoked (MBean previously unregistered by external agent)", 0,
				listener.getUnregistered().size());
	}

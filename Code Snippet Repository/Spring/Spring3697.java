	@Test
	public void testWithSuppliedMBeanServer() throws Exception {
		MBeanExporter exporter = new MBeanExporter();
		exporter.setBeans(getBeanMap());
		exporter.setServer(server);
		try {
			start(exporter);
			assertIsRegistered("The bean was not registered with the MBeanServer",
					ObjectNameManager.getInstance(OBJECT_NAME));
		}
		finally {
   			exporter.destroy();
		}
	}

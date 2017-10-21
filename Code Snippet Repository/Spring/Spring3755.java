	@Test
	public void testLocatePlatformMBeanServer() {
		MBeanServer server = null;
		try {
			server = JmxUtils.locateMBeanServer();
		}
		finally {
			if (server != null) {
				MBeanServerFactory.releaseMBeanServer(server);
			}
		}
	}

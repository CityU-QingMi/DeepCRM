	@Test
	public void testRegisterNullNotificationListenerType() throws Exception {
		Map<String, NotificationListener> listeners = new HashMap<>();
		// put null in as a value...
		listeners.put("*", null);
		MBeanExporter exporter = new MBeanExporter();

		thrown.expect(IllegalArgumentException.class);
		exporter.setNotificationListenerMappings(listeners);
	}

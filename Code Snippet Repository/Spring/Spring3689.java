	@Test
	public void testRegisterNotificationListenerForNonExistentMBean() throws Exception {
		Map<String, NotificationListener> listeners = new HashMap<>();
		NotificationListener dummyListener = new NotificationListener() {
			@Override
			public void handleNotification(Notification notification, Object handback) {
				throw new UnsupportedOperationException();
			}
		};
		// the MBean with the supplied object name does not exist...
		listeners.put("spring:type=Test", dummyListener);
		MBeanExporter exporter = new MBeanExporter();
		exporter.setBeans(getBeanMap());
		exporter.setServer(server);
		exporter.setNotificationListenerMappings(listeners);
		try {
			start(exporter);
			fail("Must have thrown an MBeanExportException when registering a " +
					"NotificationListener on a non-existent MBean.");
		}
		catch (MBeanExportException expected) {
			assertTrue(expected.contains(InstanceNotFoundException.class));
		}
	}

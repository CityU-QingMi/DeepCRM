	@SuppressWarnings({"", ""})
	@Test
	public void testRegisterNotificationListenerForMBean() throws Exception {
		ObjectName objectName = ObjectName.getInstance("spring:name=Test");
		JmxTestBean bean = new JmxTestBean();

		Map<String, Object> beans = new HashMap<>();
		beans.put(objectName.getCanonicalName(), bean);

		CountingAttributeChangeNotificationListener listener = new CountingAttributeChangeNotificationListener();

		Map notificationListeners = new HashMap();
		notificationListeners.put(objectName, listener);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);
		exporter.setNotificationListenerMappings(notificationListeners);
		start(exporter);

		// update the attribute
		String attributeName = "Name";
		server.setAttribute(objectName, new Attribute(attributeName, "Rob Harrop"));
		assertEquals("Listener not notified", 1, listener.getCount(attributeName));
	}

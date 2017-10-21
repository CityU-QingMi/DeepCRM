	@Test
	public void testRegisterNotificationListenerWithHandback() throws Exception {
		String objectName = "spring:name=Test";
		JmxTestBean bean = new JmxTestBean();

		Map<String, Object> beans = new HashMap<>();
		beans.put(objectName, bean);

		CountingAttributeChangeNotificationListener listener = new CountingAttributeChangeNotificationListener();
		Object handback = new Object();

		NotificationListenerBean listenerBean = new NotificationListenerBean();
		listenerBean.setNotificationListener(listener);
		listenerBean.setMappedObjectName("spring:name=Test");
		listenerBean.setHandback(handback);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);
		exporter.setNotificationListeners(new NotificationListenerBean[] { listenerBean });
		start(exporter);

		// update the attribute
		String attributeName = "Name";
		server.setAttribute(ObjectNameManager.getInstance("spring:name=Test"), new Attribute(attributeName,
				"Rob Harrop"));

		assertEquals("Listener not notified", 1, listener.getCount(attributeName));
		assertEquals("Handback object not transmitted correctly", handback, listener.getLastHandback(attributeName));
	}

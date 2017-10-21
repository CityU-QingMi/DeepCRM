	@Test
	public void testNotificationListenerRegistrar() throws Exception {
		ObjectName objectName = ObjectName.getInstance("spring:name=Test");
		JmxTestBean bean = new JmxTestBean();

		Map<String, Object> beans = new HashMap<>();
		beans.put(objectName.getCanonicalName(), bean);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);
		start(exporter);

		CountingAttributeChangeNotificationListener listener = new CountingAttributeChangeNotificationListener();

		NotificationListenerRegistrar registrar = new NotificationListenerRegistrar();
		registrar.setServer(server);
		registrar.setNotificationListener(listener);
		registrar.setMappedObjectName(objectName);
		registrar.afterPropertiesSet();

		// update the attribute
		String attributeName = "Name";
		server.setAttribute(objectName, new Attribute(attributeName, "Rob Harrop"));
		assertEquals("Listener not notified", 1, listener.getCount(attributeName));

		registrar.destroy();

		// try to update the attribute again
		server.setAttribute(objectName, new Attribute(attributeName, "Rob Harrop"));
		assertEquals("Listener notified after destruction", 1, listener.getCount(attributeName));
	}

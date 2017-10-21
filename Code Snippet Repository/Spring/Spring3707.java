	@Test
	public void testNotificationListenerRegistrarWithMultipleNames() throws Exception {
		ObjectName objectName = ObjectName.getInstance("spring:name=Test");
		ObjectName objectName2 = ObjectName.getInstance("spring:name=Test2");
		JmxTestBean bean = new JmxTestBean();
		JmxTestBean bean2 = new JmxTestBean();

		Map<String, Object> beans = new HashMap<>();
		beans.put(objectName.getCanonicalName(), bean);
		beans.put(objectName2.getCanonicalName(), bean2);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);
		start(exporter);

		CountingAttributeChangeNotificationListener listener = new CountingAttributeChangeNotificationListener();

		NotificationListenerRegistrar registrar = new NotificationListenerRegistrar();
		registrar.setServer(server);
		registrar.setNotificationListener(listener);
		//registrar.setMappedObjectNames(new Object[] {objectName, objectName2});
		registrar.setMappedObjectNames("spring:name=Test", "spring:name=Test2");
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

	@SuppressWarnings("")
	@Test
	public void testRegisterNotificationListenerWithFilter() throws Exception {
		ObjectName objectName = ObjectName.getInstance("spring:name=Test");
		JmxTestBean bean = new JmxTestBean();

		Map<String, Object> beans = new HashMap<>();
		beans.put(objectName.getCanonicalName(), bean);

		CountingAttributeChangeNotificationListener listener = new CountingAttributeChangeNotificationListener();

		NotificationListenerBean listenerBean = new NotificationListenerBean();
		listenerBean.setNotificationListener(listener);
		listenerBean.setNotificationFilter(new NotificationFilter() {
			@Override
			public boolean isNotificationEnabled(Notification notification) {
				if (notification instanceof AttributeChangeNotification) {
					AttributeChangeNotification changeNotification = (AttributeChangeNotification) notification;
					return "Name".equals(changeNotification.getAttributeName());
				}
				else {
					return false;
				}
			}
		});

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);
		exporter.setNotificationListeners(new NotificationListenerBean[] { listenerBean });
		start(exporter);

		// update the attributes
		String nameAttribute = "Name";
		String ageAttribute = "Age";

		server.setAttribute(objectName, new Attribute(nameAttribute, "Rob Harrop"));
		server.setAttribute(objectName, new Attribute(ageAttribute, new Integer(90)));

		assertEquals("Listener not notified for Name", 1, listener.getCount(nameAttribute));
		assertEquals("Listener incorrectly notified for Age", 0, listener.getCount(ageAttribute));
	}

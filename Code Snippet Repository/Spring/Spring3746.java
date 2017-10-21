	public void testSendVanillaNotification() throws Exception {
		StubSpringModelMBean mbean = new StubSpringModelMBean();
		Notification notification = new Notification("network.alarm.router", mbean, 1872);
		ObjectName objectName = createObjectName();

		NotificationPublisher publisher = new ModelMBeanNotificationPublisher(mbean, objectName, mbean);
		publisher.sendNotification(notification);

		assertNotNull(mbean.getActualNotification());
		assertSame("The exact same Notification is not being passed through from the publisher to the mbean.", notification, mbean.getActualNotification());
		assertSame("The 'source' property of the Notification is not being set to the ObjectName of the associated MBean.", objectName, mbean.getActualNotification().getSource());
	}

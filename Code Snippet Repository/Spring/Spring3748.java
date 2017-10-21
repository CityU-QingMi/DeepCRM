	public void testSendAttributeChangeNotificationWhereSourceIsNotTheManagedResource() throws Exception {
		StubSpringModelMBean mbean = new StubSpringModelMBean();
		Notification notification = new AttributeChangeNotification(this, 1872, System.currentTimeMillis(), "Shall we break for some tea?", "agree", "java.lang.Boolean", Boolean.FALSE, Boolean.TRUE);
		ObjectName objectName = createObjectName();

		NotificationPublisher publisher = new ModelMBeanNotificationPublisher(mbean, objectName, mbean);
		publisher.sendNotification(notification);

		assertNotNull(mbean.getActualNotification());
		assertTrue(mbean.getActualNotification() instanceof AttributeChangeNotification);
		assertSame("The exact same Notification is not being passed through from the publisher to the mbean.", notification, mbean.getActualNotification());
		assertSame("The 'source' property of the Notification is *wrongly* being set to the ObjectName of the associated MBean.", this, mbean.getActualNotification().getSource());
	}

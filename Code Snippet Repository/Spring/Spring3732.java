	@Test
	public void testNotificationMetadata() throws Exception {
		ModelMBeanInfo info = (ModelMBeanInfo) getMBeanInfo();
		MBeanNotificationInfo[] notifications = info.getNotifications();
		assertEquals("Incorrect number of notifications", 1, notifications.length);
		assertEquals("Incorrect notification name", "My Notification", notifications[0].getName());

		String[] notifTypes = notifications[0].getNotifTypes();

		assertEquals("Incorrect number of notification types", 2, notifTypes.length);
		assertEquals("Notification type.foo not found", "type.foo", notifTypes[0]);
		assertEquals("Notification type.bar not found", "type.bar", notifTypes[1]);
	}

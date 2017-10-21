	@Test
	public void testSimpleBeanRegisteredManually() throws Exception {
		// start the MBeanExporter
		ConfigurableApplicationContext ctx = loadContext("org/springframework/jmx/export/notificationPublisherTests.xml");
		MBeanExporter exporter = (MBeanExporter) ctx.getBean("exporter");
		MyNotificationPublisher publisher = new MyNotificationPublisher();
		exporter.registerManagedResource(publisher, ObjectNameManager.getInstance("spring:type=Publisher2"));
		this.server.addNotificationListener(ObjectNameManager.getInstance("spring:type=Publisher2"), listener, null,
				null);

		assertNotNull("NotificationPublisher should not be null", publisher.getNotificationPublisher());
		publisher.sendNotification();
		assertEquals("Notification not sent", 1, listener.count);
	}

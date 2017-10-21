	@Test
	public void testLazyInit() throws Exception {
		// start the MBeanExporter
		ConfigurableApplicationContext ctx = loadContext("org/springframework/jmx/export/notificationPublisherLazyTests.xml");
		assertFalse("Should not have instantiated the bean yet", ctx.getBeanFactory().containsSingleton("publisher"));

		// need to touch the MBean proxy
		server.getAttribute(ObjectNameManager.getInstance("spring:type=Publisher"), "Name");
		this.server.addNotificationListener(ObjectNameManager.getInstance("spring:type=Publisher"), listener, null,
				null);

		MyNotificationPublisher publisher = (MyNotificationPublisher) ctx.getBean("publisher");
		assertNotNull("NotificationPublisher should not be null", publisher.getNotificationPublisher());
		publisher.sendNotification();
		assertEquals("Notification not sent", 1, listener.count);
	}

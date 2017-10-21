	@Test
	public void testMBean() throws Exception {
		// start the MBeanExporter
		ConfigurableApplicationContext ctx = loadContext("org/springframework/jmx/export/notificationPublisherTests.xml");
		this.server.addNotificationListener(ObjectNameManager.getInstance("spring:type=PublisherMBean"), listener,
				null, null);

		MyNotificationPublisherMBean publisher = (MyNotificationPublisherMBean) ctx.getBean("publisherMBean");
		publisher.sendNotification();
		assertEquals("Notification not sent", 1, listener.count);
	}

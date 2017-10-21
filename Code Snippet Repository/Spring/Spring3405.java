	@Test
	public void simpleEventXmlConfig() {
		this.context = new ClassPathXmlApplicationContext(
				"org/springframework/context/event/simple-event-configuration.xml");

		TestEvent event = new TestEvent(this, "test");
		TestEventListener listener = this.context.getBean(TestEventListener.class);
		this.eventCollector = getEventCollector(this.context);

		this.eventCollector.assertNoEventReceived(listener);
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(listener, event);
		this.eventCollector.assertTotalEventsCount(1);
	}

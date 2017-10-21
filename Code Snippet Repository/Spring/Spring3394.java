	@Test
	public void simpleEventJavaConfig() {
		load(TestEventListener.class);
		TestEvent event = new TestEvent(this, "test");
		TestEventListener listener = this.context.getBean(TestEventListener.class);

		this.eventCollector.assertNoEventReceived(listener);
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(listener, event);
		this.eventCollector.assertTotalEventsCount(1);

		this.eventCollector.clear();
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(listener, event);
		this.eventCollector.assertTotalEventsCount(1);
	}

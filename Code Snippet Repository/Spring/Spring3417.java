	@Test
	public void contextEventsAreReceived() {
		load(ContextEventListener.class);
		ContextEventListener listener = this.context.getBean(ContextEventListener.class);

		List<Object> events = this.eventCollector.getEvents(listener);
		assertEquals("Wrong number of initial context events", 1, events.size());
		assertEquals(ContextRefreshedEvent.class, events.get(0).getClass());

		this.context.stop();
		List<Object> eventsAfterStop = this.eventCollector.getEvents(listener);
		assertEquals("Wrong number of context events on shutdown", 2, eventsAfterStop.size());
		assertEquals(ContextStoppedEvent.class, eventsAfterStop.get(1).getClass());
		this.eventCollector.assertTotalEventsCount(2);
	}

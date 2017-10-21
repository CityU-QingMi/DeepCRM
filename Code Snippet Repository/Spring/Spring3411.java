	@Test
	public void listenerWithResolvableTypeEvent() {
		load(ResolvableTypeEventListener.class);
		ResolvableTypeEventListener listener = this.context.getBean(ResolvableTypeEventListener.class);

		this.eventCollector.assertNoEventReceived(listener);
		GenericEventPojo<String> event = new GenericEventPojo<>("TEST");
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(listener, event);
		this.eventCollector.assertTotalEventsCount(1);
	}

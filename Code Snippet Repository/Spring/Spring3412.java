	@Test
	public void listenerWithResolvableTypeEventWrongGeneric() {
		load(ResolvableTypeEventListener.class);
		ResolvableTypeEventListener listener = this.context.getBean(ResolvableTypeEventListener.class);

		this.eventCollector.assertNoEventReceived(listener);
		GenericEventPojo<Long> event = new GenericEventPojo<>(123L);
		this.context.publishEvent(event);
		this.eventCollector.assertNoEventReceived(listener);
		this.eventCollector.assertTotalEventsCount(0);
	}

	@Test
	public void collectionReplyNullValue() {
		load(TestEventListener.class, ReplyEventListener.class);
		AnotherTestEvent event = new AnotherTestEvent(this, Arrays.asList(null, "test"));
		ReplyEventListener replyEventListener = this.context.getBean(ReplyEventListener.class);
		TestEventListener listener = this.context.getBean(TestEventListener.class);

		this.eventCollector.assertNoEventReceived(listener);
		this.eventCollector.assertNoEventReceived(replyEventListener);
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(replyEventListener, event);
		this.eventCollector.assertEvent(listener, "test");
		this.eventCollector.assertTotalEventsCount(2);
	}

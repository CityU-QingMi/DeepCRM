	@Test
	public void arrayReply() {
		load(TestEventListener.class, ReplyEventListener.class);
		AnotherTestEvent event = new AnotherTestEvent(this, new String[]{"first", "second"});
		ReplyEventListener replyEventListener = this.context.getBean(ReplyEventListener.class);
		TestEventListener listener = this.context.getBean(TestEventListener.class);

		this.eventCollector.assertNoEventReceived(listener);
		this.eventCollector.assertNoEventReceived(replyEventListener);
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(replyEventListener, event);
		this.eventCollector.assertEvent(listener, "first", "second"); // reply
		this.eventCollector.assertTotalEventsCount(3);
	}

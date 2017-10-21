	@Test
	public void collectionReply() {
		load(TestEventListener.class, ReplyEventListener.class);
		Set<Object> replies = new LinkedHashSet<>();
		replies.add("first");
		replies.add(4L);
		replies.add("third");
		AnotherTestEvent event = new AnotherTestEvent(this, replies);
		ReplyEventListener replyEventListener = this.context.getBean(ReplyEventListener.class);
		TestEventListener listener = this.context.getBean(TestEventListener.class);

		this.eventCollector.assertNoEventReceived(listener);
		this.eventCollector.assertNoEventReceived(replyEventListener);
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(replyEventListener, event);
		this.eventCollector.assertEvent(listener, "first", "third"); // reply (no listener for 4L)
		this.eventCollector.assertTotalEventsCount(3);
	}

	@Test
	@SuppressWarnings("")
	public void testReceiveTwoRequestCallsWhenOnSubscribe() {
		Subscriber<DataBuffer> subscriber = mock(Subscriber.class);
		doAnswer(new SubscriptionAnswer()).when(subscriber).onSubscribe(isA(Subscription.class));

		TestListenerReadPublisher publisher = new TestListenerReadPublisher();
		publisher.subscribe(subscriber);
		publisher.onDataAvailable();

		assertTrue(publisher.getReadCalls() == 2);
	}

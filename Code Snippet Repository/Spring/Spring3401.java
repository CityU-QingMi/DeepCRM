	@Test
	public void asyncProcessingApplied() throws InterruptedException {
		loadAsync(AsyncEventListener.class);

		String threadName = Thread.currentThread().getName();
		AnotherTestEvent event = new AnotherTestEvent(this, threadName);
		AsyncEventListener listener = this.context.getBean(AsyncEventListener.class);
		this.eventCollector.assertNoEventReceived(listener);

		this.context.publishEvent(event);
		this.countDownLatch.await(2, TimeUnit.SECONDS);
		this.eventCollector.assertEvent(listener, event);
		this.eventCollector.assertTotalEventsCount(1);
	}

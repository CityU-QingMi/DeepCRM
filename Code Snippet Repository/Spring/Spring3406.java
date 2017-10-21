	@Test
	public void exceptionNotPropagatedWithAsync() throws InterruptedException {
		loadAsync(ExceptionEventListener.class);
		AnotherTestEvent event = new AnotherTestEvent(this, "fail");
		ExceptionEventListener listener = this.context.getBean(ExceptionEventListener.class);
		this.eventCollector.assertNoEventReceived(listener);

		this.context.publishEvent(event);
		this.countDownLatch.await(2, TimeUnit.SECONDS);

		this.eventCollector.assertEvent(listener, event);
		this.eventCollector.assertTotalEventsCount(1);
	}

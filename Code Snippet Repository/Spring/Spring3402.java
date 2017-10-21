	@Test
	public void asyncProcessingAppliedWithInterfaceProxy() throws InterruptedException {
		doLoad(AsyncConfigurationWithInterfaces.class, SimpleProxyTestBean.class);

		String threadName = Thread.currentThread().getName();
		AnotherTestEvent event = new AnotherTestEvent(this, threadName);
		SimpleService listener = this.context.getBean(SimpleService.class);
		this.eventCollector.assertNoEventReceived(listener);

		this.context.publishEvent(event);
		this.countDownLatch.await(2, TimeUnit.SECONDS);
		this.eventCollector.assertEvent(listener, event);
		this.eventCollector.assertTotalEventsCount(1);
	}

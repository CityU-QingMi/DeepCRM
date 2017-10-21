	private void stopActiveMqBrokerAndAwait() throws Exception {
		logger.debug("Stopping ActiveMQ broker and will await shutdown");
		if (!this.activeMQBroker.isStarted()) {
			logger.debug("Broker not running");
			return;
		}
		final CountDownLatch latch = new CountDownLatch(1);
		this.activeMQBroker.addShutdownHook(new Runnable() {
			public void run() {
				latch.countDown();
			}
		});
		this.activeMQBroker.stop();
		assertTrue("Broker did not stop", latch.await(5, TimeUnit.SECONDS));
		logger.debug("Broker stopped");
	}

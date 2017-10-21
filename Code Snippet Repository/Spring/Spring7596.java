	@After
	public void tearDown() throws Exception {
		try {
			this.client.shutdown();
		}
		catch (Throwable ex) {
			logger.error("Failed to shut client", ex);
		}
		final CountDownLatch latch = new CountDownLatch(1);
		this.activeMQBroker.addShutdownHook(latch::countDown);
		logger.debug("Stopping ActiveMQ broker and will await shutdown");
		this.activeMQBroker.stop();
		if (!latch.await(5, TimeUnit.SECONDS)) {
			logger.debug("ActiveMQ broker did not shut in the expected time.");
		}
	}

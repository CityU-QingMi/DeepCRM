	@After
	public void tearDown() throws Exception {
		try {
			logger.debug("STOMP broker relay stats: " + this.relay.getStatsInfo());
			this.relay.stop();
		}
		finally {
			stopActiveMqBrokerAndAwait();
		}
	}

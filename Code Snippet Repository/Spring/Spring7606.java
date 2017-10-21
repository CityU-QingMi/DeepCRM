	private void startActiveMqBroker() throws Exception {
		this.activeMQBroker = new BrokerService();
		this.activeMQBroker.addConnector("stomp://localhost:" + this.port);
		this.activeMQBroker.setStartAsync(false);
		this.activeMQBroker.setPersistent(false);
		this.activeMQBroker.setUseJmx(false);
		this.activeMQBroker.getSystemUsage().getMemoryUsage().setLimit(1024 * 1024 * 5);
		this.activeMQBroker.getSystemUsage().getTempUsage().setLimit(1024 * 1024 * 5);
		this.activeMQBroker.start();
	}

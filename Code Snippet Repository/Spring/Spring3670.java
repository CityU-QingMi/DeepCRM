	@Override
	public void onSetUp() throws Exception {
		runTests = false;
		Assume.group(TestGroup.JMXMP);
		runTests = true;
		super.onSetUp();
		this.connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(getServiceUrl(), null, getServer());
		try {
			this.connectorServer.start();
		}
		catch (BindException ex) {
			System.out.println("Skipping remote JMX tests because binding to local port ["
					+ SERVICE_PORT + "] failed: " + ex.getMessage());
			runTests = false;
		}
	}

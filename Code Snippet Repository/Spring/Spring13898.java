	private void registerEndpoint(ServerEndpointConfig endpointConfig) {
		ServerContainer serverContainer = getServerContainer();
		Assert.state(serverContainer != null, "No ServerContainer set");
		try {
			if (logger.isInfoEnabled()) {
				logger.info("Registering ServerEndpointConfig: " + endpointConfig);
			}
			serverContainer.addEndpoint(endpointConfig);
		}
		catch (DeploymentException ex) {
			throw new IllegalStateException("Failed to register ServerEndpointConfig: " + endpointConfig, ex);
		}
	}

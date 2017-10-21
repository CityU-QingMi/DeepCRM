	private void registerEndpoint(Class<?> endpointClass) {
		ServerContainer serverContainer = getServerContainer();
		Assert.state(serverContainer != null, "No ServerContainer set");
		try {
			if (logger.isInfoEnabled()) {
				logger.info("Registering @ServerEndpoint class: " + endpointClass);
			}
			serverContainer.addEndpoint(endpointClass);
		}
		catch (DeploymentException ex) {
			throw new IllegalStateException("Failed to register @ServerEndpoint class: " + endpointClass, ex);
		}
	}

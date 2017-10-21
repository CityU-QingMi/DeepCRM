	private Object createEndpoint(ServerEndpointRegistration registration, ComponentProviderService provider,
			WebSocketContainer container, TyrusWebSocketEngine engine) throws DeploymentException {

		DirectFieldAccessor accessor = new DirectFieldAccessor(engine);
		Object sessionListener = accessor.getPropertyValue("sessionListener");
		Object clusterContext = accessor.getPropertyValue("clusterContext");
		try {
			if (constructorWithBooleanArgument) {
				// Tyrus 1.11+
				return constructor.newInstance(registration.getEndpoint(), registration, provider, container,
						"/", registration.getConfigurator(), sessionListener, clusterContext, null, Boolean.TRUE);
			}
			else {
				return constructor.newInstance(registration.getEndpoint(), registration, provider, container,
						"/", registration.getConfigurator(), sessionListener, clusterContext, null);
			}
		}
		catch (Exception ex) {
			throw new HandshakeFailureException("Failed to register " + registration, ex);
		}
	}

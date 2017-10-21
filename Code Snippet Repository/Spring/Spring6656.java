		public void configureEndpoint(Object listenerContainer) {
			if (listenerContainer instanceof JmsMessageEndpointManager) {
				setupJcaMessageContainer((JmsMessageEndpointManager) listenerContainer);
			}
			else {
				throw new IllegalArgumentException("Could not configure endpoint with the specified container '" +
						listenerContainer + "' Only JMS (" + AbstractMessageListenerContainer.class.getName() +
						" subclass) or JCA (" + JmsMessageEndpointManager.class.getName() + ") are supported.");
			}
		}

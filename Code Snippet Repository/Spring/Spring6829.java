	protected void populateActivationSpecProperties(BeanWrapper bw, JmsActivationSpecConfig config) {
		String destinationName = config.getDestinationName();
		if (destinationName != null) {
			boolean pubSubDomain = config.isPubSubDomain();
			Object destination = destinationName;
			if (this.destinationResolver != null) {
				try {
					destination = this.destinationResolver.resolveDestinationName(null, destinationName, pubSubDomain);
				}
				catch (JMSException ex) {
					throw new DestinationResolutionException(
							"Cannot resolve destination name [" + destinationName + "]", ex);
				}
			}
			bw.setPropertyValue("destination", destination);
			bw.setPropertyValue("destinationType", pubSubDomain ? Topic.class.getName() : Queue.class.getName());
		}

		if (bw.isWritableProperty("subscriptionDurability")) {
			bw.setPropertyValue("subscriptionDurability", config.isSubscriptionDurable() ? "Durable" : "NonDurable");
		}
		else if (config.isSubscriptionDurable()) {
			// Standard JCA 1.5 "subscriptionDurability" apparently not supported...
			throw new IllegalArgumentException("Durable subscriptions not supported by underlying provider");
		}
		if (config.isSubscriptionShared()) {
			throw new IllegalArgumentException("Shared subscriptions not supported for JCA-driven endpoints");
		}

		if (config.getSubscriptionName() != null) {
			bw.setPropertyValue("subscriptionName", config.getSubscriptionName());
		}
		if (config.getClientId() != null) {
			bw.setPropertyValue("clientId", config.getClientId());
		}
		if (config.getMessageSelector() != null) {
			bw.setPropertyValue("messageSelector", config.getMessageSelector());
		}
		applyAcknowledgeMode(bw, config.getAcknowledgeMode());
	}

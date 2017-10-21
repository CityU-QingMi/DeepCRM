	@Override
	public void afterPropertiesSet() throws ResourceException {
		if (getResourceAdapter() == null) {
			throw new IllegalArgumentException("Property 'resourceAdapter' is required");
		}
		if (this.messageListenerSet) {
			setMessageEndpointFactory(this.endpointFactory);
		}
		if (this.activationSpecConfig != null) {
			setActivationSpec(
					this.activationSpecFactory.createActivationSpec(getResourceAdapter(), this.activationSpecConfig));
		}

		super.afterPropertiesSet();
	}

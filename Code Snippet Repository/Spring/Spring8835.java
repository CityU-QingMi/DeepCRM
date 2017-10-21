	@Override
	public void afterPropertiesSet() throws ResourceException {
		if (getResourceAdapter() == null) {
			throw new IllegalArgumentException("Property 'resourceAdapter' is required");
		}
		if (getMessageEndpointFactory() == null) {
			throw new IllegalArgumentException("Property 'messageEndpointFactory' is required");
		}
		ActivationSpec activationSpec = getActivationSpec();
		if (activationSpec == null) {
			throw new IllegalArgumentException("Property 'activationSpec' is required");
		}

		if (activationSpec.getResourceAdapter() == null) {
			activationSpec.setResourceAdapter(getResourceAdapter());
		}
		else if (activationSpec.getResourceAdapter() != getResourceAdapter()) {
			throw new IllegalArgumentException("ActivationSpec [" + activationSpec +
					"] is associated with a different ResourceAdapter: " + activationSpec.getResourceAdapter());
		}
	}

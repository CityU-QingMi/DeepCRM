	@Override
	public Class<? extends Endpoint> getEndpointClass() {
		if (this.endpoint != null) {
			return this.endpoint.getClass();
		}
		else {
			Assert.state(this.endpointProvider != null, "No endpoint set");
			return this.endpointProvider.getHandlerType();
		}
	}

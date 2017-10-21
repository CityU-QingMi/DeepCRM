	protected ResourceWebHandler getRequestHandler() {
		ResourceWebHandler handler = new ResourceWebHandler();
		if (this.resourceChainRegistration != null) {
			handler.setResourceResolvers(this.resourceChainRegistration.getResourceResolvers());
			handler.setResourceTransformers(this.resourceChainRegistration.getResourceTransformers());
		}
		handler.setLocations(this.locations);
		if (this.cacheControl != null) {
			handler.setCacheControl(this.cacheControl);
		}
		return handler;
	}

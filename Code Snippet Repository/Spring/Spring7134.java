	@Override
	public D resolveDestination(String name) throws DestinationResolutionException {
		D destination = this.resolvedDestinationCache.get(name);
		if (destination == null) {
			Assert.state(this.targetDestinationResolver != null, "No target DestinationResolver set");
			destination = this.targetDestinationResolver.resolveDestination(name);
			this.resolvedDestinationCache.put(name, destination);
		}
		return destination;
	}

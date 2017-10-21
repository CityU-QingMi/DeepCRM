	public void addELResolver(ELResolver resolver) throws IllegalStateException {
		if (resolver == null) {
			throw new IllegalArgumentException("ELResolver was null");
		}
		if (this.instantiated) {
			throw new IllegalStateException(
					"cannot call addELResolver after the first request has been made");
		}
		this.resolvers.add(resolver);
	}

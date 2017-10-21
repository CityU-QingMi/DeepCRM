	public ResourceChainRegistration addResolver(ResourceResolver resolver) {
		Assert.notNull(resolver, "The provided ResourceResolver should not be null");
		this.resolvers.add(resolver);
		if (resolver instanceof VersionResourceResolver) {
			this.hasVersionResolver = true;
		}
		else if (resolver instanceof PathResourceResolver) {
			this.hasPathResolver = true;
		}
		else if (resolver instanceof WebJarsResourceResolver) {
			this.hasWebjarsResolver = true;
		}
		return this;
	}

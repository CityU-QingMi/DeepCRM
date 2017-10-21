	protected List<ResourceResolver> getResourceResolvers() {
		if (!this.hasPathResolver) {
			List<ResourceResolver> result = new ArrayList<>(this.resolvers);
			if (isWebJarsAssetLocatorPresent && !this.hasWebjarsResolver) {
				result.add(new WebJarsResourceResolver());
			}
			result.add(new PathResourceResolver());
			return result;
		}
		return this.resolvers;
	}

	protected List<ResourceTransformer> getResourceTransformers() {
		if (this.hasVersionResolver && !this.hasCssLinkTransformer) {
			List<ResourceTransformer> result = new ArrayList<>(this.transformers);
			boolean hasTransformers = !this.transformers.isEmpty();
			boolean hasCaching = hasTransformers && this.transformers.get(0) instanceof CachingResourceTransformer;
			result.add(hasCaching ? 1 : 0, new CssLinkResourceTransformer());
			return result;
		}
		return this.transformers;
	}

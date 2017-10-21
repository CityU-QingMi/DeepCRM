	@Nullable
	private ResourceTransformer getNext() {
		Assert.state(this.index <= this.transformers.size(),
				"Current index exceeds the number of configured ResourceTransformers");

		if (this.index == (this.transformers.size() - 1)) {
			return null;
		}
		this.index++;
		return this.transformers.get(this.index);
	}

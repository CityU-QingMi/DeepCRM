	@Nullable
	private ResourceTransformer getNext() {
		Assert.state(this.index <= this.transformers.size(),
				"Current index exceeds the number of configured ResourceTransformer's");

		if (this.index == (this.transformers.size() - 1)) {
			return null;
		}

		this.index++;
		return this.transformers.get(this.index);
	}

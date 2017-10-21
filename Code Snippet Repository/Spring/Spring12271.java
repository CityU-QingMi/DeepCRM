	@Nullable
	private ResourceResolver getNext() {
		Assert.state(this.index <= this.resolvers.size(),
				"Current index exceeds the number of configured ResourceResolvers");

		if (this.index == (this.resolvers.size() - 1)) {
			return null;
		}
		this.index++;
		return this.resolvers.get(this.index);
	}

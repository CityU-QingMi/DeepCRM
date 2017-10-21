	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("size", size())
				.append("maxSize", getMaxSize())
				.append("parentContextCount", getParentContextCount())
				.append("hitCount", getHitCount())
				.append("missCount", getMissCount())
				.toString();
	}

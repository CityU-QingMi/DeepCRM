	@Override
	public String toString() {
		// @formatter:off
		return new ToStringBuilder(this)
				.append("type", type)
				.append("testDescriptor", testDescriptor)
				.append("payload", payload)
				.toString();
		// @formatter:on
	}

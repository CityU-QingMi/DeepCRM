	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);

		this.explicitConfigParams.forEach(builder::append);

		// @formatter:off
		this.configParamsFromFile.stringPropertyNames().stream()
				.filter(key -> !this.explicitConfigParams.containsKey(key))
				.forEach(key -> builder.append(key, this.configParamsFromFile.getProperty(key)));
		// @formatter:on

		return builder.toString();
	}

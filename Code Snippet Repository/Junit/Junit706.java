	@Override
	public String toString() {
		// @formatter:off
		return new ToStringBuilder(this)
				.append("uniqueId", this.uniqueId)
				.append("parentId", this.parentId)
				.append("displayName", this.displayName)
				.append("legacyReportingName", this.legacyReportingName)
				.append("source", this.source)
				.append("tags", this.tags)
				.append("type", this.type)
				.toString();
		// @formatter:on
	}

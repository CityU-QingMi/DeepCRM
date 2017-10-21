	@Override
	public String toString() {
		// @formatter:off
		return new ToStringCreator(this)
			.append("id", this.getId())
			.append("name", this.name)
			.append("age", this.age)
			.append("eyeColor", this.eyeColor)
			.append("likesPets", this.likesPets)
			.append("favoriteNumber", this.favoriteNumber)
			.toString();
		// @formatter:on
	}

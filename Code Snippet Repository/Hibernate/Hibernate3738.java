	public AbstractQuerySpace(
			String uid,
			Disposition disposition,
			ExpandingQuerySpaces querySpaces,
			boolean canJoinsBeRequired) {
		this.uid = uid;
		this.disposition = disposition;
		this.querySpaces = querySpaces;
		this.canJoinsBeRequired = canJoinsBeRequired;
	}

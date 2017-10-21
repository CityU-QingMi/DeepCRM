	protected LoadPlanImpl(
			List<? extends Return> returns,
			QuerySpaces querySpaces,
			Disposition disposition,
			boolean areLazyAttributesForceFetched) {
		this.returns = returns;
		this.querySpaces = querySpaces;
		this.disposition = disposition;
		this.areLazyAttributesForceFetched = areLazyAttributesForceFetched;
	}

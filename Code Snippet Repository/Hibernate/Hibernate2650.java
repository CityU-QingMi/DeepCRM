	protected FromElement(
			FromClause fromClause,
			FromElement origin,
			String alias) {
		this.fromClause = fromClause;
		this.origin = origin;
		this.classAlias = alias;
		this.tableAlias = origin.getTableAlias();
		super.initialize( fromClause.getWalker() );

	}

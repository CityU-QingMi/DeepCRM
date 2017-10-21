	protected IdsClauseBuilder(
			Dialect dialect,
			Type identifierType,
			TypeResolver typeResolver,
			String[] columns,
			List<Object[]> ids) {
		this.dialect = dialect;
		this.identifierType = identifierType;
		this.typeResolver = typeResolver;
		this.columns = columns;
		this.ids = ids;
	}

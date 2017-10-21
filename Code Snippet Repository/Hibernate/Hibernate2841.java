	public CteValuesListBuilder(
			String tableName,
			String[] columns,
			List<Object[]> ids) {
		this.tableName = tableName;
		this.columns = columns;
		this.ids = ids;

		this.cteStatement = buildStatement();
	}

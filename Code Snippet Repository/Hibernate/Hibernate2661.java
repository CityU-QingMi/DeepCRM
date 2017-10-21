	public FromElementFactory(
			FromClause fromClause,
			FromElement origin,
			String path,
			String classAlias,
			String[] columns,
			boolean implied) {
		this( fromClause, origin, path );
		this.classAlias = classAlias;
		this.columns = columns;
		this.implied = implied;
		collection = true;
	}

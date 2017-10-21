	public Table(
			Identifier catalog,
			Identifier schema,
			Identifier physicalTableName,
			boolean isAbstract) {
		this.catalog = catalog;
		this.schema = schema;
		this.name = physicalTableName;
		this.isAbstract = isAbstract;
	}

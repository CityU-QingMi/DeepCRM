	public Namespace(Database database, Name name) {
		this.database = database;
		this.name = name;

		this.physicalName = new Name(
				database.getPhysicalNamingStrategy()
						.toPhysicalCatalogName( name.getCatalog(), database.getJdbcEnvironment() ),
				database.getPhysicalNamingStrategy()
						.toPhysicalSchemaName( name.getSchema(), database.getJdbcEnvironment() )
		);

		log.debugf(
				"Created database namespace [logicalName=%s, physicalName=%s]",
				name.toString(),
				physicalName.toString()
		);
	}

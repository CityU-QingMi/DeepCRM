	public SimpleAuxiliaryDatabaseObject(
			Namespace namespace,
			String[] createStrings,
			String[] dropStrings,
			Set<String> dialectScopes) {
		this(
				dialectScopes,
				extractName( namespace.getPhysicalName().getCatalog() ),
				extractName( namespace.getPhysicalName().getSchema() ),
				createStrings,
				dropStrings
		);
	}

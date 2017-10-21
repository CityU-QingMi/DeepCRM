	public SimpleAuxiliaryDatabaseObject(
			Set<String> dialectScopes,
			String catalogName,
			String schemaName,
			String[] createStrings,
			String[] dropStrings) {
		super( dialectScopes );
		this.catalogName = catalogName;
		this.schemaName = schemaName;
		this.createStrings = createStrings;
		this.dropStrings = dropStrings;
	}

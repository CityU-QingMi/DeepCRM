	@Override
	public String sqlDropString(
			Dialect dialect,
			String defaultCatalog,
			String defaultSchema) {
		return null;
//		return dialect.getUniqueDelegate().getAlterTableToDropUniqueKeyCommand(
//				this, defaultCatalog, defaultSchema
//		);
	}

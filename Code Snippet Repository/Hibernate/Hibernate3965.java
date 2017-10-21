	@Override
	public String sqlCreateString(
			Dialect dialect,
			Mapping p,
			String defaultCatalog,
			String defaultSchema) {
		return null;
//		return dialect.getUniqueDelegate().getAlterTableToAddUniqueKeyCommand(
//				this, defaultCatalog, defaultSchema
//		);
	}

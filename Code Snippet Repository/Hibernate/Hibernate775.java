	@Override
	public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source) {
		Identifier userProvidedIdentifier = source.getUserProvidedIdentifier();
		return userProvidedIdentifier != null ? userProvidedIdentifier : toIdentifier(
				NamingHelper.INSTANCE.generateHashedFkName(
						"FK",
						source.getTableName(),
						source.getReferencedTableName(),
						source.getColumnNames()
				),
				source.getBuildingContext()
		);
	}

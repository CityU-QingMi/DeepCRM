	@Override
	public Identifier determineUniqueKeyName(ImplicitUniqueKeyNameSource source) {
		Identifier userProvidedIdentifier = source.getUserProvidedIdentifier();
		return userProvidedIdentifier != null ? userProvidedIdentifier : toIdentifier(
				NamingHelper.INSTANCE.generateHashedConstraintName(
						"UK",
						source.getTableName(),
						source.getColumnNames()
				),
				source.getBuildingContext()
		);
	}

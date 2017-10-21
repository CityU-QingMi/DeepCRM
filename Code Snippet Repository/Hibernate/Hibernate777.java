	@Override
	public Identifier determineIndexName(ImplicitIndexNameSource source) {
		Identifier userProvidedIdentifier = source.getUserProvidedIdentifier();
		return userProvidedIdentifier != null ? userProvidedIdentifier : toIdentifier(
				NamingHelper.INSTANCE.generateHashedConstraintName(
						"IDX",
						source.getTableName(),
						source.getColumnNames()
				),
				source.getBuildingContext()
		);
	}

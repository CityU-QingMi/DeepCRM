	@Override
	public Identifier determineCollectionTableName(ImplicitCollectionTableNameSource source) {
		Identifier identifier = toIdentifier(
				source.getOwningPhysicalTableName().getText() + "_" + transformAttributePath( source.getOwningAttributePath() ),
				source.getBuildingContext()
		);
		if ( source.getOwningPhysicalTableName().isQuoted() ) {
			identifier = Identifier.quote( identifier );
		}
		return identifier;
	}

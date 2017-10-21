	@Override
	public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
		final String ownerPortion = source.getOwningPhysicalTableName();
		final String ownedPortion;
		if ( source.getNonOwningPhysicalTableName() != null ) {
			ownedPortion = source.getNonOwningPhysicalTableName();
		}
		else {
			ownedPortion = transformAttributePath( source.getAssociationOwningAttributePath() );
		}

		return toIdentifier( ownerPortion + "_" + ownedPortion, source.getBuildingContext() );
	}

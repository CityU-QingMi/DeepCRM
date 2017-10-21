	@Override
	public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
		if ( source.getAssociationOwningAttributePath() != null ) {
			final String name = source.getOwningPhysicalTableName()
					+ '_'
					+ transformAttributePath( source.getAssociationOwningAttributePath() );

			return toIdentifier( name, source.getBuildingContext() );
		}

		return super.determineJoinTableName( source );
	}

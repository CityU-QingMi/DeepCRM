		@Override
		public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
			final String ownerPortion = transformEntityName( source.getOwningEntityNaming() );
			final String ownedPortion;
			if ( source.getNonOwningEntityNaming() != null ) {
				ownedPortion = transformEntityName( source.getNonOwningEntityNaming() );
			}
			else {
				ownedPortion = transformAttributePath( source.getAssociationOwningAttributePath() );
			}

			return toIdentifier( ownerPortion + "_" + ownedPortion, source.getBuildingContext() );
		}

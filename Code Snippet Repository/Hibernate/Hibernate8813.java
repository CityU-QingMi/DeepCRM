		@Override
		public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
			final String entityPortion = transformEntityName( source.getEntityNaming() );
			final String name;
			if ( source.getAttributePath() == null ) {
				name = entityPortion + "_" + source.getReferencedColumnName();
			}
			else {
				name = entityPortion + "_"
						+ transformAttributePath( source.getAttributePath() )
						+ "_" + source.getReferencedColumnName();
			}
			return toIdentifier( name, source.getBuildingContext() );
		}

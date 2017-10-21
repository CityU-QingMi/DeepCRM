	@Override
	public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
		if ( source.getAttributePath() != null ) {
			return toIdentifier(
					transformAttributePath( source.getAttributePath() ),
					source.getBuildingContext()
			);
		}

		return super.determineJoinColumnName( source );
	}

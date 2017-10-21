	@Override
	public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
		// legacy JPA-based naming strategy preferred to use {TableName}_{ReferencedColumnName}
		// where JPA was later clarified to prefer {EntityName}_{ReferencedColumnName}.
		//
		// The spec-compliant one implements the clarified {EntityName}_{ReferencedColumnName}
		// naming.  Here we implement the older {TableName}_{ReferencedColumnName} naming
		final String name;

//		if ( source.getNature() == ImplicitJoinColumnNameSource.Nature.ENTITY
//				&& source.getAttributePath() != null ) {
//			// many-to-one /  one-to-one
//			//
//			// legacy naming used the attribute name here, following suit with legacy hbm naming
//			//
//			// NOTE : attribute path being null here would be an error, so for now don't bother checking
//			name = transformAttributePath( source.getAttributePath() );
//		}
//		else if ( source.getNature() == ImplicitJoinColumnNameSource.Nature.ELEMENT_COLLECTION
		if ( source.getNature() == ImplicitJoinColumnNameSource.Nature.ELEMENT_COLLECTION
				|| source.getAttributePath() == null ) {
			name = source.getReferencedTableName().getText()
					+ '_'
					+ source.getReferencedColumnName().getText();
		}
		else {
			name = transformAttributePath( source.getAttributePath() )
					+ '_'
					+ source.getReferencedColumnName().getText();
		}

		return toIdentifier( name, source.getBuildingContext() );
	}

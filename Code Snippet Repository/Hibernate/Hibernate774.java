	@Override
	public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
		// JPA states we should use the following as default:
		//
		//	(1) if there is a "referencing relationship property":
		//		"The concatenation of the following: the name of the referencing relationship
		// 			property or field of the referencing entity or embeddable class; "_"; the
		// 			name of the referenced primary key column."
		//
		//	(2) if there is no such "referencing relationship property", or if the association is
		// 			an element collection:
		//     "The concatenation of the following: the name of the entity; "_"; the name of the
		// 			referenced primary key column"

		// todo : we need to better account for "referencing relationship property"

		final String name;

		if ( source.getNature() == ImplicitJoinColumnNameSource.Nature.ELEMENT_COLLECTION
				|| source.getAttributePath() == null ) {
			name = transformEntityName( source.getEntityNaming() )
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

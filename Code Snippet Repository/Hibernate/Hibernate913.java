	private static void bindSimpleValueType(
			MappingDocument mappingDocument,
			HibernateTypeSource typeSource,
			SimpleValue simpleValue) {
		if ( mappingDocument.getBuildingOptions().useNationalizedCharacterData() ) {
			simpleValue.makeNationalized();
		}

		final TypeResolution typeResolution = resolveType( mappingDocument, typeSource );
		if ( typeResolution == null ) {
			// no explicit type info was found
			return;
		}

		if ( CollectionHelper.isNotEmpty( typeResolution.parameters ) ) {
			simpleValue.setTypeParameters( typeResolution.parameters );
		}

		if ( typeResolution.typeName != null ) {
			simpleValue.setTypeName( typeResolution.typeName );
		}
	}

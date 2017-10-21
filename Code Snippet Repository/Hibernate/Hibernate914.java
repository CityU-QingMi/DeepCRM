	private static TypeResolution resolveType(
			MappingDocument sourceDocument,
			HibernateTypeSource typeSource) {
		if ( StringHelper.isEmpty( typeSource.getName() ) ) {
			return null;
		}

		String typeName = typeSource.getName();
		Properties typeParameters = new Properties();;

		final TypeDefinition typeDefinition = sourceDocument.getMetadataCollector().getTypeDefinition( typeName );
		if ( typeDefinition != null ) {
			// the explicit name referred to a type-def
			typeName = typeDefinition.getTypeImplementorClass().getName();
			if ( typeDefinition.getParameters() != null ) {
				typeParameters.putAll( typeDefinition.getParameters() );
			}
		}
//		else {
//			final BasicType basicType = sourceDocument.getMetadataCollector().getTypeResolver().basic( typeName );
//			if ( basicType == null ) {
//				throw new MappingException(
//						String.format(
//								Locale.ENGLISH,
//								"Mapping named an explicit type [%s] which could not be resolved",
//								typeName
//						),
//						sourceDocument.getOrigin()
//				);
//			}
//		}

		// parameters on the property mapping should override parameters in the type-def
		if ( typeSource.getParameters() != null ) {
			typeParameters.putAll( typeSource.getParameters() );
		}

		return new TypeResolution( typeName, typeParameters );
	}

	@Override
	public Identifier determineCollectionTableName(ImplicitCollectionTableNameSource source) {
		// JPA states we should use the following as default:
		//      "The concatenation of the name of the containing entity and the name of the
		//       collection attribute, separated by an underscore.
		// aka:
		//     if owning entity has a JPA entity name: {OWNER JPA ENTITY NAME}_{COLLECTION ATTRIBUTE NAME}
		//     otherwise: {OWNER ENTITY NAME}_{COLLECTION ATTRIBUTE NAME}
		final String entityName = transformEntityName( source.getOwningEntityNaming() );

		final String name = entityName
				+ '_'
				+ transformAttributePath( source.getOwningAttributePath() );

		return toIdentifier( name, source.getBuildingContext() );
	}

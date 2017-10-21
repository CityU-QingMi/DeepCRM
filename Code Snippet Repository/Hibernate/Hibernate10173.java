	private static List<String> identifierPropertyNames(SessionFactoryImplementor sessionFactory, String entityName) {
		final String identifierPropertyName = sessionFactory.getMetamodel().entityPersister( entityName ).getIdentifierPropertyName();
		if ( identifierPropertyName != null ) {
			// Single id.
			return Arrays.asList( identifierPropertyName );
		}
		final Type identifierType = sessionFactory.getMetamodel().entityPersister( entityName ).getIdentifierType();
		if ( identifierType instanceof EmbeddedComponentType ) {
			// Multiple ids.
			final EmbeddedComponentType embeddedComponentType = (EmbeddedComponentType) identifierType;
			return Arrays.asList( embeddedComponentType.getPropertyNames() );
		}
		return Collections.emptyList();
	}

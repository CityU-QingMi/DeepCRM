	public static String determinePropertyName(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			String entityName,
			String propertyName) {
		final SessionFactoryImplementor sessionFactory = versionsReader.getSessionImplementor().getFactory();

		if ( AuditId.IDENTIFIER_PLACEHOLDER.equals( propertyName ) ) {
			final String identifierPropertyName = sessionFactory.getMetamodel().entityPersister( entityName ).getIdentifierPropertyName();
			propertyName = enversService.getAuditEntitiesConfiguration().getOriginalIdPropName() + "." + identifierPropertyName;
		}
		else {
			final List<String> identifierPropertyNames = identifierPropertyNames( sessionFactory, entityName );
			if ( identifierPropertyNames.contains( propertyName ) ) {
				propertyName = enversService.getAuditEntitiesConfiguration().getOriginalIdPropName() + "." + propertyName;
			}
			else if ( propertyName != null ) {
				// if property starts with an identifier prefix ( e.g. embedded ids ), substitute with the originalId property
				// because Envers performs replacement this automatically during the mapping.
				for ( String identifierPropertyName : identifierPropertyNames ) {
					if ( propertyName.startsWith( identifierPropertyName + "." ) ) {
						propertyName = enversService.getAuditEntitiesConfiguration().getOriginalIdPropName() +
								propertyName.substring( identifierPropertyName.length() );
						break;
					}
				}
			}
		}

		return propertyName;
	}

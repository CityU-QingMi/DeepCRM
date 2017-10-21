	public static EntityNamingSourceImpl extractEntityNamingSource(
			MappingDocument sourceMappingDocument,
			EntityInfo jaxbEntityMapping) {
		final String className = sourceMappingDocument.qualifyClassName( jaxbEntityMapping.getName() );
		final String entityName;
		final String jpaEntityName;
		if ( StringHelper.isNotEmpty( jaxbEntityMapping.getEntityName() ) ) {
			entityName = jaxbEntityMapping.getEntityName();
			jpaEntityName = jaxbEntityMapping.getEntityName();
		}
		else {
			entityName = className;
			jpaEntityName = StringHelper.unqualify( className );
		}
		return new EntityNamingSourceImpl( entityName, className, jpaEntityName );
	}

	protected void initIdentifierPropertyPaths(
			final String path,
			final EntityType etype,
			final String[] columns,
			final String[] columnReaders,
			final String[] columnReaderTemplates,
			final Mapping factory) throws MappingException {

		Type idtype = etype.getIdentifierOrUniqueKeyType( factory );
		String idPropName = etype.getIdentifierOrUniqueKeyPropertyName( factory );
		boolean hasNonIdentifierPropertyNamedId = hasNonIdentifierPropertyNamedId( etype, factory );

		if ( etype.isReferenceToPrimaryKey() ) {
			if ( !hasNonIdentifierPropertyNamedId ) {
				String idpath1 = extendPath( path, EntityPersister.ENTITY_ID );
				addPropertyPath( idpath1, idtype, columns, columnReaders, columnReaderTemplates, null );
				initPropertyPaths( idpath1, idtype, columns, columnReaders, columnReaderTemplates, null, factory );
			}
		}

		if ( idPropName != null ) {
			String idpath2 = extendPath( path, idPropName );
			addPropertyPath( idpath2, idtype, columns, columnReaders, columnReaderTemplates, null );
			initPropertyPaths( idpath2, idtype, columns, columnReaders, columnReaderTemplates, null, factory );
		}
	}

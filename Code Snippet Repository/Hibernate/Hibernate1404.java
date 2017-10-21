	public static void bindQuery(
			NamedQuery queryAnn,
			MetadataBuildingContext context,
			boolean isDefault) {
		if ( queryAnn == null ) return;
		if ( BinderHelper.isEmptyAnnotationValue( queryAnn.name() ) ) {
			throw new AnnotationException( "A named query must have a name when used in class or package level" );
		}
		//EJBQL Query
		QueryHintDefinition hints = new QueryHintDefinition( queryAnn.hints() );
		String queryName = queryAnn.query();
		NamedQueryDefinition queryDefinition = new NamedQueryDefinitionBuilder( queryAnn.name() )
				.setLockOptions( hints.determineLockOptions( queryAnn ) )
				.setQuery( queryName )
				.setCacheable( hints.getBoolean( queryName, QueryHints.CACHEABLE ) )
				.setCacheRegion( hints.getString( queryName, QueryHints.CACHE_REGION ) )
				.setTimeout( hints.getTimeout( queryName ) )
				.setFetchSize( hints.getInteger( queryName, QueryHints.FETCH_SIZE ) )
				.setFlushMode( hints.getFlushMode( queryName ) )
				.setCacheMode( hints.getCacheMode( queryName ) )
				.setReadOnly( hints.getBoolean( queryName, QueryHints.READ_ONLY ) )
				.setComment( hints.getString( queryName, QueryHints.COMMENT ) )
				.setParameterTypes( null )
				.createNamedQueryDefinition();

		if ( isDefault ) {
			context.getMetadataCollector().addDefaultQuery( queryDefinition );
		}
		else {
			context.getMetadataCollector().addNamedQuery( queryDefinition );
		}

		if ( LOG.isDebugEnabled() ) {
			LOG.debugf( "Binding named query: %s => %s", queryDefinition.getName(), queryDefinition.getQueryString() );
		}
	}

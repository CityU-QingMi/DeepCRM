	public static void bindQuery(
			org.hibernate.annotations.NamedQuery queryAnn,
			MetadataBuildingContext context) {
		if ( queryAnn == null ) {
			return;
		}

		if ( BinderHelper.isEmptyAnnotationValue( queryAnn.name() ) ) {
			throw new AnnotationException( "A named query must have a name when used in class or package level" );
		}

		FlushMode flushMode;
		flushMode = getFlushMode( queryAnn.flushMode() );

		NamedQueryDefinition query = new NamedQueryDefinitionBuilder().setName( queryAnn.name() )
				.setQuery( queryAnn.query() )
				.setCacheable( queryAnn.cacheable() )
				.setCacheRegion(
						BinderHelper.isEmptyAnnotationValue( queryAnn.cacheRegion() ) ?
								null :
								queryAnn.cacheRegion()
				)
				.setTimeout( queryAnn.timeout() < 0 ? null : queryAnn.timeout() )
				.setFetchSize( queryAnn.fetchSize() < 0 ? null : queryAnn.fetchSize() )
				.setFlushMode( flushMode )
				.setCacheMode( getCacheMode( queryAnn.cacheMode() ) )
				.setReadOnly( queryAnn.readOnly() )
				.setComment( BinderHelper.isEmptyAnnotationValue( queryAnn.comment() ) ? null : queryAnn.comment() )
				.setParameterTypes( null )
				.createNamedQueryDefinition();

		context.getMetadataCollector().addNamedQuery( query );
		if ( LOG.isDebugEnabled() ) {
			LOG.debugf( "Binding named query: %s => %s", query.getName(), query.getQueryString() );
		}
	}

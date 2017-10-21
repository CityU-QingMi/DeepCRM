	private void fillInNamedQueryBuilder(NamedQueryDefinitionBuilder builder, org.hibernate.query.Query query) {
		builder.setQuery( query.getQueryString() )
				.setComment( query.getComment() )
				.setCacheable( query.isCacheable() )
				.setCacheRegion( query.getCacheRegion() )
				.setCacheMode( query.getCacheMode() )
				.setReadOnly( query.isReadOnly() )
				.setFlushMode( query.getHibernateFlushMode() );

		if ( query.getQueryOptions().getFirstRow() != null ) {
			builder.setFirstResult( query.getQueryOptions().getFirstRow() );
		}

		if ( query.getQueryOptions().getMaxRows() != null ) {
			builder.setMaxResults( query.getQueryOptions().getMaxRows() );
		}

		if ( query.getQueryOptions().getTimeout() != null ) {
			builder.setTimeout( query.getQueryOptions().getTimeout() );
		}

		if ( query.getQueryOptions().getFetchSize() != null ) {
			builder.setFetchSize( query.getQueryOptions().getFetchSize() );
		}
	}

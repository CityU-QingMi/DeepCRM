	@SuppressWarnings({ "" })
	private void renderFetches(
			StringBuilder jpaqlQuery,
			RenderingContext renderingContext,
			Collection<Fetch> fetches) {
		if ( fetches == null ) {
			return;
		}

		for ( Fetch fetch : fetches ) {
			( (FromImplementor) fetch ).prepareAlias( renderingContext );
			jpaqlQuery.append( renderJoinType( fetch.getJoinType() ) )
					.append( "fetch " )
					.append( ( (FromImplementor) fetch ).renderTableExpression( renderingContext ) );

			renderFetches( jpaqlQuery, renderingContext, fetch.getFetches() );
		}
	}

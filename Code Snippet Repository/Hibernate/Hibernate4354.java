	@SuppressWarnings({ "" })
	private void renderJoins(
			StringBuilder jpaqlQuery,
			RenderingContext renderingContext,
			Collection<Join<?,?>> joins) {
		if ( joins == null ) {
			return;
		}

		for ( Join join : joins ) {
			( (FromImplementor) join ).prepareAlias( renderingContext );
			jpaqlQuery.append( renderJoinType( join.getJoinType() ) )
					.append( ( (FromImplementor) join ).renderTableExpression( renderingContext ) );
			renderJoins( jpaqlQuery, renderingContext, join.getJoins() );
			renderFetches( jpaqlQuery, renderingContext, join.getFetches() );
		}
	}

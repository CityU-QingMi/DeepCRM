	@SuppressWarnings({ "" })
	private void renderFromClause(StringBuilder jpaqlQuery, RenderingContext renderingContext) {
		jpaqlQuery.append( " from " );
		String sep = "";
		for ( Root root : getRoots() ) {
			( (FromImplementor) root ).prepareAlias( renderingContext );
			jpaqlQuery.append( sep );
			jpaqlQuery.append( ( (FromImplementor) root ).renderTableExpression( renderingContext ) );
			sep = ", ";
		}

		for ( Root root : getRoots() ) {
			renderJoins( jpaqlQuery, renderingContext, root.getJoins() );
			if (root instanceof RootImpl) {
				Set<TreatedRoot> treats = ((RootImpl)root).getTreats();
				for ( TreatedRoot treat : treats ) {
					renderJoins( jpaqlQuery, renderingContext, treat.getJoins() );
				}
			}
			renderFetches( jpaqlQuery, renderingContext, root.getFetches() );
		}

		if ( isSubQuery ) {
			if ( correlationRoots != null ) {
				for ( FromImplementor<?,?> correlationRoot : correlationRoots ) {
					final FromImplementor correlationParent = correlationRoot.getCorrelationParent();
					correlationParent.prepareAlias( renderingContext );
					final String correlationRootAlias = correlationParent.getAlias();
					for ( Join<?,?> correlationJoin : correlationRoot.getJoins() ) {
						final JoinImplementor correlationJoinImpl = (JoinImplementor) correlationJoin;
						// IMPL NOTE: reuse the sep from above!
						jpaqlQuery.append( sep );
						correlationJoinImpl.prepareAlias( renderingContext );
						jpaqlQuery.append( correlationRootAlias )
								.append( '.' )
								.append( correlationJoinImpl.getAttribute().getName() )
								.append( " as " )
								.append( correlationJoinImpl.getAlias() );
						sep = ", ";
						renderJoins( jpaqlQuery, renderingContext, correlationJoinImpl.getJoins() );
					}
				}
			}
		}
	}

	public Set<Join<?, ?>> collectCorrelatedJoins() {
		if ( !isSubQuery ) {
			throw new IllegalStateException( "Query is not identified as sub-query" );
		}
		final Set<Join<?, ?>> correlatedJoins;
		if ( correlationRoots != null ) {
			correlatedJoins = new HashSet<Join<?,?>>();
			for ( FromImplementor<?,?> correlationRoot : correlationRoots ) {
				if (correlationRoot instanceof Join<?,?> && correlationRoot.isCorrelated()) {
					correlatedJoins.add( (Join<?,?>) correlationRoot );
				}
				correlatedJoins.addAll( correlationRoot.getJoins() );
			}
		}
		else {
			correlatedJoins = Collections.emptySet();
		}
		return correlatedJoins;
	}

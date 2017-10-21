	protected List applyProjections(final List queryResult, final Number revision) {
		final List result = new ArrayList( queryResult.size() );
		if ( hasProjection() ) {
			for (final Object qr : queryResult) {
				if ( projections.size() == 1 ) {
					// qr is the value of the projection itself
					final Pair<String, AuditProjection> projection = projections.get( 0 );
					result.add( projection.getSecond().convertQueryResult( enversService, entityInstantiator, projection.getFirst(), revision, qr ) );
				}
				else {
					// qr is an array where each of its components holds the value of corresponding projection
					Object[] qresults = (Object[]) qr;
					Object[] tresults = new Object[qresults.length];
					for ( int i = 0; i < qresults.length; i++ ) {
						final Pair<String, AuditProjection> projection = projections.get( i );
						tresults[i] = projection.getSecond().convertQueryResult( enversService, entityInstantiator, projection.getFirst(), revision, qresults[i] );
					}
					result.add( tresults );
				}
			}
		}
		else {
			entityInstantiator.addInstancesFromVersionsEntities( entityName, result, queryResult, revision );
		}
		return result;
	}

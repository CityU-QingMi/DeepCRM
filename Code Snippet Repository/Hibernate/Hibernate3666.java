	protected Object extractEntityResult(List results) {
		if ( results.size() == 0 ) {
			return null;
		}
		else if ( results.size() == 1 ) {
			return results.get( 0 );
		}
		else {
			final Object row = results.get( 0 );
			if ( row.getClass().isArray() ) {
				// the logical type of the result list is List<Object[]>.  See if the contained
				// array contains just one element, and return that if so
				final Object[] rowArray = (Object[]) row;
				if ( rowArray.length == 1 ) {
					return rowArray[0];
				}
			}
			else {
				return row;
			}
		}

		throw new HibernateException( "Unable to interpret given query results in terms of a load-entity query" );
	}

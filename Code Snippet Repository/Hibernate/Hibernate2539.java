	@Override
	public void throwQueryException() throws QueryException {
		if ( getErrorCount() > 0 ) {
		if ( recognitionExceptions.size() > 0 ) {
				throw QuerySyntaxException.convert( recognitionExceptions.get( 0 ), hql );
		}
			throw new QueryException( getErrorString(), hql );
		}
		LOG.debug( "throwQueryException() : no errors" );
	}

	@Override
	public QueryImplementor createQuery(CriteriaUpdate criteriaUpdate) {
		checkOpen();
		try {
			return criteriaCompiler().compile( (CompilableCriteria) criteriaUpdate );
		}
		catch ( RuntimeException e ) {
			throw exceptionConverter.convert( e );
		}
	}

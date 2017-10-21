	@Override
	public QueryImplementor createQuery(CriteriaDelete criteriaDelete) {
		checkOpen();
		try {
			return criteriaCompiler().compile( (CompilableCriteria) criteriaDelete );
		}
		catch ( RuntimeException e ) {
			throw exceptionConverter.convert( e );
		}
	}

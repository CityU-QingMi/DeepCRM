	@Override
	@SuppressWarnings("")
	public <T> QueryImplementor<T> createQuery(CriteriaQuery<T> criteriaQuery) {
		checkOpen();
		try {
			return (QueryImplementor<T>) criteriaCompiler().compile( (CompilableCriteria) criteriaQuery );
		}
		catch ( RuntimeException e ) {
			throw exceptionConverter.convert( e );
		}
	}

	@Override
	public List listCustomQuery(CustomQuery customQuery, QueryParameters queryParameters)
			throws HibernateException {
		checkOpen();
		CustomLoader loader = new CustomLoader( customQuery, getFactory() );

		boolean success = false;
		List results;
		try {
			results = loader.list( this, queryParameters );
			success = true;
		}
		finally {
			afterOperation( success );
		}
		temporaryPersistenceContext.clear();
		return results;
	}

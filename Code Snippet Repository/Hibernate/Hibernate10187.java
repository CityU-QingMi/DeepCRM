	public Object getSingleResult() throws AuditException, NonUniqueResultException, NoResultException {
		List result = list();

		if ( result == null || result.size() == 0 ) {
			throw new NoResultException();
		}

		if ( result.size() > 1 ) {
			throw new NonUniqueResultException();
		}

		return result.get( 0 );
	}

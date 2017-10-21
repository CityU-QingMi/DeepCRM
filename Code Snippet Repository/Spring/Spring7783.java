	@SuppressWarnings("")
	protected Session openSession() throws DataAccessResourceFailureException {
		try {
			Session session = obtainSessionFactory().openSession();
			session.setFlushMode(FlushMode.MANUAL);
			return session;
		}
		catch (HibernateException ex) {
			throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
		}
	}

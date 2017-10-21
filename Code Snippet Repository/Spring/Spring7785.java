	@SuppressWarnings("")
	protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		Session session = openSession();
		if (session == null) {
			try {
				session = sessionFactory.openSession();
				session.setFlushMode(FlushMode.MANUAL);
			}
			catch (HibernateException ex) {
				throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
			}
		}
		return session;
	}

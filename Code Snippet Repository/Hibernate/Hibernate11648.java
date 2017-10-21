	protected <T> T callWithSession(SessionFactory sessionFactory, SessionCallable<T> callable) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		try {
			T retval = callable.call((SharedSessionContractImplementor) session);
			tx.commit();
			return retval;
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

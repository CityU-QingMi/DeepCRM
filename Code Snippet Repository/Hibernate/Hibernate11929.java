	private <T> void doInSession(String hql, Map<Integer, T> result, Map<String, Object> params) {
		Session session = null;
		Transaction tx = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery( hql );
			setParameters( params, query );
			addQueryResults( result, query );
		}
		finally {
			if ( tx != null ) {
				tx.rollback();
			}
			if ( session != null ) {
				session.close();
			}
		}
	}

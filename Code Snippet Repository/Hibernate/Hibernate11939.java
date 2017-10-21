	private void retrieveAndCompare(Map<Integer, Boolean> dbexpected, Criterion spatialCriterion) {
		Session session = null;
		Transaction tx = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria( GeomEntity.class );
			criteria.add( spatialCriterion );
			compare( dbexpected, criteria.list() );
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

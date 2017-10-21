	private void storeTestObjects(Map<Integer, GeomEntity> stored) {
		Session session = null;
		Transaction tx = null;
		int id = -1;
		try {
			session = openSession();
			// Every testsuite-suite instance is committed seperately
			// to improve feedback in case of failure
			for ( TestDataElement element : testData ) {
				id = element.id;
				tx = session.beginTransaction();
				GeomEntity entity = GeomEntity.createFrom( element );
				stored.put( entity.getId(), entity );
				session.save( entity );
				tx.commit();
			}
		}
		catch ( Exception e ) {
			if ( tx != null ) {
				tx.rollback();
			}
			throw new RuntimeException( "Failed storing testsuite-suite object with id:" + id, e );
		}
		finally {
			if ( session != null ) {
				session.close();
			}
		}
	}

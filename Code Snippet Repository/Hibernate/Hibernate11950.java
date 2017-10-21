	private void retrieveNullGeometry() {
		Transaction tx = null;
		Session session = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria( GeomEntity.class );
			List<GeomEntity> retrieved = criteria.list();
			assertEquals( "Expected exactly one result", 1, retrieved.size() );
			GeomEntity entity = retrieved.get( 0 );
			assertNull( entity.getGeom() );
			tx.commit();
		}
		catch ( Exception e ) {
			if ( tx != null ) {
				tx.rollback();
			}
			throw new RuntimeException( e );
		}
		finally {
			if ( session != null ) {
				session.close();
			}
		}
	}

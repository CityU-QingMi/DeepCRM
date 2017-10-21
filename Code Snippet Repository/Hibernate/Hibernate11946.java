	private void retrieveAndCompare(Map<Integer, GeomEntity> stored) {
		int id = -1;
		Transaction tx = null;
		Session session = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			for ( GeomEntity storedEntity : stored.values() ) {
				id = storedEntity.getId();
				GeomEntity retrievedEntity = (GeomEntity) session.get( GeomEntity.class, id );
				Geometry retrievedGeometry = retrievedEntity.getGeom();
				Geometry storedGeometry = storedEntity.getGeom();
				String msg = createFailureMessage( storedEntity.getId(), storedGeometry, retrievedGeometry );
				assertTrue( msg, geometryEquality.test( storedGeometry, retrievedGeometry ) );
			}
			tx.commit();
		}
		catch ( Exception e ) {
			if ( tx != null ) {
				tx.rollback();
			}
			throw new RuntimeException( String.format( "Failure on case: %d", id ), e );
		}
		finally {
			if ( session != null ) {
				session.close();
			}
		}
	}

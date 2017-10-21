	private void retrieveAndCompare(Map<Integer, GeomEntity> stored) {
		int id = -1;
		Transaction tx = null;
		Session session = null;
		GeometryEquality geomEq = new GeometryPointEquality();
		try {
			session = openSession();
			tx = session.beginTransaction();
			for ( GeomEntity storedEntity : stored.values() ) {
				id = storedEntity.getId();
				GeomEntity retrievedEntity = (GeomEntity) session.get( GeomEntity.class, id );
				Geometry<C3DM> retrievedGeometry = retrievedEntity.getGeom();
				Geometry<C3DM> storedGeometry = storedEntity.getGeom();
				String msg = createFailureMessage( storedEntity.getId(), storedGeometry, retrievedGeometry );
				assertTrue( msg, geomEq.equals( storedGeometry, retrievedGeometry ) );
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

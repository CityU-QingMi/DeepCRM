	private void storeNullGeometry() {
		GeomEntity entity = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			entity = new GeomEntity();
			entity.setId( 1 );
			entity.setType( "NULL OBJECT" );
			session.save( entity );
			tx.commit();
		}
		catch ( Exception e ) {
			if ( tx != null ) {
				tx.rollback();
			}
			Integer id = entity != null ? entity.getId() : -1;
			throw new RuntimeException( "Failed storing testsuite-suite object with id:" + id, e );
		}
		finally {
			if ( session != null ) {
				session.close();
			}
		}
	}

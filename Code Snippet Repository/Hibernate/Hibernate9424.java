	@Override
	protected void prepareTest() throws Exception {
		try (Session session = openSession()) {
			session.getTransaction().begin();
			try {
				session.save( new MyEntity( 1L, "entity_1", new BigInteger( "3" ) ) );
				session.save( new MyEntity( 2L, "entity_2", new BigInteger( "6" ) ) );
				session.getTransaction().commit();
			}
			catch (Exception e) {
				if ( session.getTransaction().isActive() ) {
					session.getTransaction().rollback();
				}
				throw e;
			}
		}
	}

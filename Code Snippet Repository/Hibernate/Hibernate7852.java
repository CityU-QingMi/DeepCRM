	@Override
	protected void prepareTest() throws Exception {
		try (Session s = openSession()) {
			final MyEntity entity = new MyEntity( "entity" );
			s.getTransaction().begin();
			try {
				s.save( entity );

				s.getTransaction().commit();
			}
			catch (Exception e) {
				if ( s.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
					s.getTransaction().rollback();
				}
				throw e;
			}
		}
	}

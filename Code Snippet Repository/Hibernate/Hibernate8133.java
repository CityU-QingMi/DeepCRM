	@Override
	protected void prepareTest() throws Exception {
		try (Session session = openSession()) {
			session.beginTransaction();
			try {
				for ( int i = 0; i < 20; i++ ) {
					Person p1 = new Person( i, "p" + i );
					session.save( p1 );
				}
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

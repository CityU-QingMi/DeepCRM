	@Override
	protected void cleanupTest() throws Exception {
		try (Session session = openSession()) {
			session.getTransaction().begin();
			try {
				session.createQuery( "delete from MyEntity" ).executeUpdate();
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

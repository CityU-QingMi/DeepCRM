	@Override
	protected void cleanupTestData() throws Exception {
		try (Session session = openSession()) {
			session.getTransaction().begin();
			try {
				session.createQuery( "delete from TestEntity" ).executeUpdate();
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

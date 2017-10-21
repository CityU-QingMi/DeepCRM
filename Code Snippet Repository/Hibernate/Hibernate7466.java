	@Override
	protected void prepareTest() throws Exception {
		try (Session session = openSession()) {
			session.getTransaction().begin();
			TestEntity entity = new TestEntity();
			entity.setDate( new DateAttribute( System.currentTimeMillis() ) );
			try {
				session.persist( entity );
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

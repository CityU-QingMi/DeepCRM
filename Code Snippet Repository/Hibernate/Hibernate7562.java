	private void insertTestEntity(String name) {
		final TestEntity entity = new TestEntity();
		entity.setName( name );
		try (Session s = openSession()) {
			session.getTransaction().begin();
			try {
				s.save( entity );
				s.getTransaction().commit();
			}
			catch (Exception e) {
				if ( s.getTransaction().isActive() ) {
					s.getTransaction().rollback();
				}
				throw e;
			}
		}
	}

	@After
	public void tearDown() {
		try (Session s = openSession()) {
			session.getTransaction().begin();
			try {
				s.createQuery( "delete from TestEntity" ).executeUpdate();
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

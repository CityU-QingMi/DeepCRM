	@Test
	public void testFunctionWithJDBC() {
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();

		try {
			final AtomicReference<Integer> phoneCount = new AtomicReference<>();
			Session session = entityManager.unwrap( Session.class );
			session.doWork( connection -> {
				try (CallableStatement function = connection.prepareCall(
						"{ ? = call fn_count_phones(?) }" )) {
					function.registerOutParameter( 1, Types.INTEGER );
					function.setInt( 2, 1 );
					function.execute();
					phoneCount.set( function.getInt( 1 ) );
				}
			} );
			assertEquals( Integer.valueOf( 2 ), phoneCount.get() );
		}
		finally {
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
	}

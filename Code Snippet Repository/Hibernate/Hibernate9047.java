	@Test
	public void testStoredProcedureReturnValue() {
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();

		try {
			Session session = entityManager.unwrap( Session.class );
			session.doWork( new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					CallableStatement function = null;
					try {
						function = connection.prepareCall("{ ? = call fn_count_phones(?) }");
						function.registerOutParameter(1, Types.INTEGER);
						function.setInt(2, 1);
						function.execute();
						int phoneCount = function.getInt(1);
						assertEquals(2, phoneCount);
					}
					finally {
						if ( function != null ) {
							function.close();
						}
					}
				}
			} );
		}
		finally {
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
	}

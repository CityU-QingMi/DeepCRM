	@Test
	public void testBoundedLongStringAccess() {

		Session s = openSession();
		s.beginTransaction();
		try {
			s.doWork( new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					Statement statement = connection.createStatement();
					try {
						statement.executeUpdate( "SET @a='test'" );
					}
					finally {
						statement.close();
					}
				}
			}  );
			Object[] result = (Object[]) session.createSQLQuery( "SELECT @a, (@a::=20) FROM dual" ).uniqueResult();
			assertEquals("test", result[0]);
			assertEquals(20, ((Number) result[1]).intValue());

			s.getTransaction().commit();
		}
		finally {
			s.close();
		}
	}

	@Test
	public void testGeneralReturningUsage() throws Throwable {
		Session session = openSession();
		session.beginTransaction();
		Person p = new Person( "Abe", "Lincoln" );
		session.save( p );
		session.getTransaction().commit();

		final Session session2 = openSession();
		session2.beginTransaction();
		long count = session2.doReturningWork(
				new ReturningWork<Long>() {
					public Long execute(Connection connection) throws SQLException {
						// in this current form, users must handle try/catches themselves for proper resource release
						Statement statement = null;
						long personCount = 0;
						try {
							statement = ((SessionImplementor)session2).getJdbcCoordinator().getStatementPreparer().createStatement();
							ResultSet resultSet = null;
							try {
								resultSet = ((SessionImplementor)session2).getJdbcCoordinator().getResultSetReturn().extract( statement, "select count(*) from T_JDBC_PERSON" );
								resultSet.next();
								personCount = resultSet.getLong( 1 );
								assertEquals( 1L, personCount );
							}
							finally {
								releaseQuietly( ((SessionImplementor)session2), resultSet, statement );
							}
						}
						finally {
							releaseQuietly( ((SessionImplementor)session2), statement );
						}
						return personCount;
					}
				}
		);
		session2.getTransaction().commit();
		session2.close();
		assertEquals( 1L, count );

		session = openSession();
		session.beginTransaction();
		session.delete( p );
		session.getTransaction().commit();
		session.close();
	}

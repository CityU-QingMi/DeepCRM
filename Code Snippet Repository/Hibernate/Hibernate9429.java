	@Test
	public void testDialectGetColumnAliasExtractor() throws Exception {
		Session session = openSession();
		final SessionImplementor sessionImplementor = (SessionImplementor) session;
		session.beginTransaction();
		session.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						PreparedStatement ps = sessionImplementor.getJdbcCoordinator().getStatementPreparer().prepareStatement( QUERY_STRING );
						ResultSet rs = sessionImplementor.getJdbcCoordinator().getResultSetReturn().extract( ps );
						try {
							ResultSetMetaData metadata = rs.getMetaData();
							String column1Alias = getDialect().getColumnAliasExtractor().extractColumnAlias( metadata, 1 );
							String column2Alias = getDialect().getColumnAliasExtractor().extractColumnAlias( metadata, 2 );
							Assert.assertFalse( "bad dialect.getColumnAliasExtractor impl", column1Alias.equals( column2Alias ) );
						}
						finally {
							sessionImplementor.getJdbcCoordinator().getResourceRegistry().release( rs, ps );
							sessionImplementor.getJdbcCoordinator().getResourceRegistry().release( ps );
						}
					}
				}
		);
		session.getTransaction().commit();
		session.close();
	}

	private void doWork(final Integer id, final Session s) {
		s.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						PreparedStatement statement = ((SessionImplementor)s).getJdbcCoordinator().getStatementPreparer().prepareStatement( "SELECT * FROM STRANGE_TYPED_OBJECT WHERE ID=?" );
						statement.setInt(1, id.intValue());
						ResultSet resultSet = ((SessionImplementor)s).getJdbcCoordinator().getResultSetReturn().extract( statement );

						assertTrue("A row should have been returned", resultSet.next());
						assertTrue("Default value should have been mapped to null", resultSet.getObject("VALUE_ONE") == null);
						assertTrue("Default value should have been mapped to null", resultSet.getObject("VALUE_TWO") == null);
						assertEquals("Non-Default value should not be changed", resultSet.getInt("VALUE_THREE"), 5);
						assertTrue("Default value should have been mapped to null", resultSet.getObject("VALUE_FOUR") == null);
					}
				}
		);
	}

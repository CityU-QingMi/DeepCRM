	@Test
	public void testStaticResultSetClosed() throws Exception {
		ResultSet resultSet2 = mock(ResultSet.class);
		reset(this.preparedStatement);
		given(this.preparedStatement.executeQuery()).willReturn(resultSet2);
		given(this.connection.createStatement()).willReturn(this.statement);

		try {
			this.template.query("my query", new ResultSetExtractor<Object>() {
				@Override
				public Object extractData(ResultSet rs) {
					throw new InvalidDataAccessApiUsageException("");
				}
			});
			fail("Should have thrown InvalidDataAccessApiUsageException");
		}
		catch (InvalidDataAccessApiUsageException idaauex) {
			// ok
		}

		try {
			this.template.query(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					return con.prepareStatement("my query");
				}
			}, new ResultSetExtractor<Object>() {
				@Override
				public Object extractData(ResultSet rs2) {
					throw new InvalidDataAccessApiUsageException("");
				}
			});
			fail("Should have thrown InvalidDataAccessApiUsageException");
		}
		catch (InvalidDataAccessApiUsageException idaauex) {
			// ok
		}

		verify(this.resultSet).close();
		verify(resultSet2).close();
		verify(this.preparedStatement).close();
		verify(this.connection, atLeastOnce()).close();
	}

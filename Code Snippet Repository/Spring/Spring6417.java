	@Test
	public void testIgnoredWarning() throws Exception {
		String sql = "SELECT forename from custmr";
		SQLWarning warnings = new SQLWarning("My warning");

		given(this.resultSet.next()).willReturn(false);
		given(this.connection.createStatement()).willReturn(this.preparedStatement);
		given(this.preparedStatement.getWarnings()).willReturn(warnings);

		// Too long: truncation

		this.template.setIgnoreWarnings(true);
		this.template.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws java.sql.SQLException {
				rs.getByte(1);
			}
		});

		verify(this.resultSet).close();
		verify(this.preparedStatement).close();
		verify(this.connection).close();
	}

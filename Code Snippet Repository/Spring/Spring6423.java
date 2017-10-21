	@Test
	public void testExecuteClosed() throws Exception {
		given(this.resultSet.next()).willReturn(true);
		given(this.callableStatement.execute()).willReturn(true);
		given(this.callableStatement.getUpdateCount()).willReturn(-1);

		List<SqlParameter> params = new ArrayList<>();
		params.add(new SqlReturnResultSet("", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) {
				throw new InvalidDataAccessApiUsageException("");
			}

		}));

		this.thrown.expect(InvalidDataAccessApiUsageException.class);
		try {
			this.template.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection conn)
						throws SQLException {
					return conn.prepareCall("my query");
				}
			}, params);
		}
		finally {
			verify(this.resultSet).close();
			verify(this.callableStatement).close();
			verify(this.connection).close();
		}
	}

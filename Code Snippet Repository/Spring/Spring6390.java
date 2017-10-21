	@Test
	public void testExceptionComesBack() throws Exception {
		final String sql = "SELECT ID FROM CUSTMR";
		final RuntimeException runtimeException = new RuntimeException("Expected");

		given(this.resultSet.next()).willReturn(true);
		given(this.connection.createStatement()).willReturn(this.preparedStatement);

		this.thrown.expect(sameInstance(runtimeException));
		try {
			this.template.query(sql, new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) {
					throw runtimeException;
				}
			});
		}
		finally {
			verify(this.resultSet).close();
			verify(this.preparedStatement).close();
			verify(this.connection).close();
		}
	}

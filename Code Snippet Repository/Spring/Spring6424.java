	@Test
	public void testCaseInsensitiveResultsMap() throws Exception {

		given(this.callableStatement.execute()).willReturn(false);
		given(this.callableStatement.getUpdateCount()).willReturn(-1);
		given(this.callableStatement.getObject(1)).willReturn("X");

		assertTrue("default should have been NOT case insensitive",
				!this.template.isResultsMapCaseInsensitive());

		this.template.setResultsMapCaseInsensitive(true);
		assertTrue("now it should have been set to case insensitive",
				this.template.isResultsMapCaseInsensitive());

		List<SqlParameter> params = new ArrayList<>();
		params.add(new SqlOutParameter("a", 12));

		Map<String, Object> out = this.template.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection conn)
					throws SQLException {
				return conn.prepareCall("my query");
			}
		}, params);

		assertThat(out, instanceOf(LinkedCaseInsensitiveMap.class));
		assertNotNull("we should have gotten the result with upper case", out.get("A"));
		assertNotNull("we should have gotten the result with lower case", out.get("a"));
		verify(this.callableStatement).close();
		verify(this.connection).close();
	}

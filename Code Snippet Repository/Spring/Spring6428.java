	@Test
	public void testConnectionCallbackWithStatementSettings() throws Exception {
		String result = this.template.execute(new ConnectionCallback<String>() {
			@Override
			public String doInConnection(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("some SQL");
				ps.setFetchSize(10);
				ps.setMaxRows(20);
				ps.close();
				return "test";
			}
		});

		assertEquals("test", result);
		verify(this.preparedStatement).setFetchSize(10);
		verify(this.preparedStatement).setMaxRows(20);
		verify(this.preparedStatement).close();
		verify(this.connection).close();
	}

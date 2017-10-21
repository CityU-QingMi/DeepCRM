	@Test
	public void testConnectionCallback() throws Exception {
		String result = this.template.execute(new ConnectionCallback<String>() {
			@Override
			public String doInConnection(Connection con) {
				assertTrue(con instanceof ConnectionProxy);
				assertSame(JdbcTemplateTests.this.connection, ((ConnectionProxy) con).getTargetConnection());
				return "test";
			}
		});
		assertEquals("test", result);
	}

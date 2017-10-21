	@Test
	public void testSqlUpdateWithThreadConnection() throws Exception {
		final String sql = "UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = 4";
		int rowsAffected = 33;

		given(this.statement.executeUpdate(sql)).willReturn(rowsAffected);
		given(this.connection.createStatement()).willReturn(this.statement);

		int actualRowsAffected = this.template.update(sql);
		assertTrue("Actual rows affected is correct", actualRowsAffected == rowsAffected);

		verify(this.statement).close();
		verify(this.connection).close();
	}

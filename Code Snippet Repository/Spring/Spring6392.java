	@Test
	public void testSqlUpdateWithArguments() throws Exception {
		final String sql = "UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = ? and PR = ?";
		int rowsAffected = 33;
		given(this.preparedStatement.executeUpdate()).willReturn(rowsAffected);

		int actualRowsAffected = this.template.update(sql,
				new Object[] {4, new SqlParameterValue(Types.NUMERIC, 2, new Float(1.4142))});
		assertTrue("Actual rows affected is correct", actualRowsAffected == rowsAffected);
		verify(this.preparedStatement).setObject(1, 4);
		verify(this.preparedStatement).setObject(2, new Float(1.4142), Types.NUMERIC, 2);
		verify(this.preparedStatement).close();
		verify(this.connection).close();
	}

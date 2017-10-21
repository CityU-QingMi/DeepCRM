	@Test
	public void testUpdateCount() throws Exception {
		final String sql = "UPDATE INVOICE SET DATE_DISPATCHED = SYSDATE WHERE ID = ?";
		int idParam = 11111;
		given(this.preparedStatement.executeUpdate()).willReturn(1);
		Dispatcher d = new Dispatcher(idParam, sql);
		int rowsAffected = this.template.update(d);
		assertTrue("1 update affected 1 row", rowsAffected == 1);
		verify(this.preparedStatement).setInt(1, idParam);
		verify(this.preparedStatement).close();
		verify(this.connection).close();
	}

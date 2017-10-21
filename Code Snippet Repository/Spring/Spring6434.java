	@Test
	public void testSetParameterValueWithNullAndUnknownTypeOnInformix() throws SQLException {
		StatementCreatorUtils.shouldIgnoreGetParameterType = true;
		Connection con = mock(Connection.class);
		DatabaseMetaData dbmd = mock(DatabaseMetaData.class);
		given(preparedStatement.getConnection()).willReturn(con);
		given(con.getMetaData()).willReturn(dbmd);
		given(dbmd.getDatabaseProductName()).willReturn("Informix Dynamic Server");
		given(dbmd.getDriverName()).willReturn("Informix Driver");
		StatementCreatorUtils.setParameterValue(preparedStatement, 1, SqlTypeValue.TYPE_UNKNOWN, null, null);
		verify(dbmd).getDatabaseProductName();
		verify(dbmd).getDriverName();
		verify(preparedStatement).setObject(1, null);
		StatementCreatorUtils.shouldIgnoreGetParameterType = false;
	}

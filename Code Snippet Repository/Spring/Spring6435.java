	@Test
	public void testSetParameterValueWithNullAndUnknownTypeOnDerbyEmbedded() throws SQLException {
		StatementCreatorUtils.shouldIgnoreGetParameterType = true;
		Connection con = mock(Connection.class);
		DatabaseMetaData dbmd = mock(DatabaseMetaData.class);
		given(preparedStatement.getConnection()).willReturn(con);
		given(con.getMetaData()).willReturn(dbmd);
		given(dbmd.getDatabaseProductName()).willReturn("Apache Derby");
		given(dbmd.getDriverName()).willReturn("Apache Derby Embedded Driver");
		StatementCreatorUtils.setParameterValue(preparedStatement, 1, SqlTypeValue.TYPE_UNKNOWN, null, null);
		verify(dbmd).getDatabaseProductName();
		verify(dbmd).getDriverName();
		verify(preparedStatement).setNull(1, Types.VARCHAR);
		StatementCreatorUtils.shouldIgnoreGetParameterType = false;
	}

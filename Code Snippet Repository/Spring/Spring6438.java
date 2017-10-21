	@Test
	public void testSetParameterValueWithNullAndVendorSpecificType() throws SQLException {
		StatementCreatorUtils.shouldIgnoreGetParameterType = true;
		Connection con = mock(Connection.class);
		DatabaseMetaData dbmd = mock(DatabaseMetaData.class);
		given(preparedStatement.getConnection()).willReturn(con);
		given(dbmd.getDatabaseProductName()).willReturn("Oracle");
		given(dbmd.getDriverName()).willReturn("Oracle Driver");
		given(con.getMetaData()).willReturn(dbmd);
		StatementCreatorUtils.setParameterValue(preparedStatement, 1, Types.OTHER, null, null);
		verify(preparedStatement).setNull(1, Types.NULL);
		StatementCreatorUtils.shouldIgnoreGetParameterType = false;
	}

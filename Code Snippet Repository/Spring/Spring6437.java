	@Test
	public void testSetParameterValueWithStringAndVendorSpecificType() throws SQLException {
		Connection con = mock(Connection.class);
		DatabaseMetaData dbmd = mock(DatabaseMetaData.class);
		given(preparedStatement.getConnection()).willReturn(con);
		given(dbmd.getDatabaseProductName()).willReturn("Oracle");
		given(con.getMetaData()).willReturn(dbmd);
		StatementCreatorUtils.setParameterValue(preparedStatement, 1, Types.OTHER, null, "test");
		verify(preparedStatement).setString(1, "test");
	}

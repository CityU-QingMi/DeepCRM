	@Test
	public void testSetParameterValueWithNullAndGetParameterTypeWorking() throws SQLException {
		ParameterMetaData pmd = mock(ParameterMetaData.class);
		given(preparedStatement.getParameterMetaData()).willReturn(pmd);
		given(pmd.getParameterType(1)).willReturn(Types.SMALLINT);
		StatementCreatorUtils.setParameterValue(preparedStatement, 1, SqlTypeValue.TYPE_UNKNOWN, null, null);
		verify(pmd).getParameterType(1);
		verify(preparedStatement, never()).getConnection();
		verify(preparedStatement).setNull(1, Types.SMALLINT);
	}

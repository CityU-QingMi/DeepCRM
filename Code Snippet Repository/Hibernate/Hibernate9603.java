	public static PreparedStatement generateProxy(Clob value) {
		return generateProxy(
				new PreparedStatementProxy<Clob>( "setClob", value ) {
					@Override
					protected void checkValue(Clob arg) throws SQLException {
						Assert.assertEquals( extractString( getValue() ), extractString( arg ) );
					}
				}
		);
	}

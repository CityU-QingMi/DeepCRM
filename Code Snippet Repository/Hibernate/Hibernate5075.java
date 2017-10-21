	@Override
	public void nullSafeSet(
			CallableStatement statement, Object value, String name, SharedSessionContractImplementor session) throws SQLException {
		if ( canDoSetting() ) {
			((ProcedureParameterNamedBinder) userType).nullSafeSet( statement, value, name, session );
		}
		else {
			throw new UnsupportedOperationException(
					"Type [" + userType + "] does support parameter binding by name"
			);
		}
	}

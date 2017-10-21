	@Override
	public void nullSafeSet(
			PreparedStatement statement,
			Object value,
			int index,
			SharedSessionContractImplementor session) throws HibernateException, SQLException {
		if ( value == null ) {
			statement.setNull( index, Types.NUMERIC );
			statement.setNull( index + 1, Types.VARCHAR );
		}
		else {
			MonetaryAmount currency = (MonetaryAmount) value;
			statement.setBigDecimal( index, currency.getValue() );
			statement.setString( index + 1, currency.getCurrency().getCurrencyCode() );
		}
	}

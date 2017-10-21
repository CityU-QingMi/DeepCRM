	@Override
	public Object nullSafeGet(
			ResultSet resultSet,
			String[] names,
			SharedSessionContractImplementor session,
			Object owner) throws HibernateException, SQLException {

		BigDecimal value = resultSet.getBigDecimal( names[0] );
		if ( resultSet.wasNull() ) {
			return null;
		}
		String cur = resultSet.getString( names[1] );
		Currency userCurrency = Currency.getInstance( cur );

		return new MonetaryAmount( value, userCurrency );
	}

	public void nullSafeSet(
			PreparedStatement st, Object value, int index,
			SharedSessionContractImplementor session
	) throws HibernateException, SQLException {
		MonetaryAmount ma = (MonetaryAmount) value;
		BigDecimal amt = ma == null ? null : ma.getAmount();
		Currency cur = ma == null ? null : ma.getCurrency();
		StandardBasicTypes.BIG_DECIMAL.nullSafeSet( st, amt, index, session );
		StandardBasicTypes.CURRENCY.nullSafeSet( st, cur, index + 1, session );
	}

	public void setPropertyValue(Object component, int property, Object value)
			throws HibernateException {
		MonetaryAmount ma = (MonetaryAmount) component;
		if ( property == 0 ) {
			ma.setAmount( (BigDecimal) value );
		}
		else {
			ma.setCurrency( (Currency) value );
		}
	}

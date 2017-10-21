	@Override
	public void setPropertyValue(Object component, int property, Object value)
			throws HibernateException {
		MonetoryAmount ma = (MonetoryAmount) component;
		if ( property==0 ) {
			ma.setAmount( (BigDecimal) value );
		}
		else {
			ma.setCurrency( (Currency) value );
		}
	}

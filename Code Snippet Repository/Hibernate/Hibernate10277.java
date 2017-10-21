	public void setPropertyValue(final Object component, final int property, final Object value)
			throws HibernateException {
		Component comp = (Component) component;
		if ( property == 0 ) {
			comp.setProp1( (String) value );
		}
		else {
			comp.setProp2( (Integer) value );
		}
	}

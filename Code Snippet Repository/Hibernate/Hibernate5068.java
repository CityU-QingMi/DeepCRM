	@Override
	public boolean[] toColumnNullness(Object value, Mapping mapping) {
		boolean[] result = new boolean[getColumnSpan( mapping )];
		if ( value == null ) {
			return result;
		}
		Object[] values = getPropertyValues( value, EntityMode.POJO ); //TODO!!!!!!!
		int loc = 0;
		Type[] propertyTypes = getSubtypes();
		for ( int i = 0; i < propertyTypes.length; i++ ) {
			boolean[] propertyNullness = propertyTypes[i].toColumnNullness( values[i], mapping );
			System.arraycopy( propertyNullness, 0, result, loc, propertyNullness.length );
			loc += propertyNullness.length;
		}
		return result;
	}

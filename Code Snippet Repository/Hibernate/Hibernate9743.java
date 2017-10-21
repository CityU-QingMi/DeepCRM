	private static Method getMethod(Object bean, String propertyName) {
		final StringBuilder sb = new StringBuilder( "get" ).append( Character.toUpperCase( propertyName.charAt( 0 ) ) );
		if ( propertyName.length() > 1 ) {
			sb.append( propertyName.substring( 1 ) );
		}
		final String getterName = sb.toString();
		for ( Method m : bean.getClass().getMethods() ) {
			if ( getterName.equals( m.getName() ) && m.getParameterCount() == 0 ) {
				return m;
			}
		}
		return null;
	}

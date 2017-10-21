	public static long getLongBeanProperty(final Object bean, final String propertyName) throws NoSuchFieldException {
		validateArgs( bean, propertyName );
		final Object o = getBeanProperty( bean, propertyName );
		if ( o == null ) {
			throw new NoSuchFieldException( propertyName );
		}
		else if ( !(o instanceof Number) ) {
			throw new IllegalArgumentException( propertyName + " not an Number" );
		}
		return ((Number) o).longValue();
	}

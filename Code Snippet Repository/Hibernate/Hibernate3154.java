	@Override
	public void setProperty(String propertyName, Object value) {
		checkOpen();

		if ( !( value instanceof Serializable ) ) {
			log.warnf( "Property '" + propertyName + "' is not serializable, value won't be set." );
			return;
		}

		properties.put( propertyName, value );
		applyProperties();
	}

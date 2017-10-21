	public Property getProperty(String propertyName) throws MappingException {
		Iterator iter = getPropertyClosureIterator();
		Property identifierProperty = getIdentifierProperty();
		if ( identifierProperty != null
				&& identifierProperty.getName().equals( StringHelper.root( propertyName ) ) ) {
			return identifierProperty;
		}
		else {
			return getProperty( propertyName, iter );
		}
	}

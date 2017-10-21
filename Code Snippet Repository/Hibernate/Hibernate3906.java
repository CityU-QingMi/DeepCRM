	private Property getProperty(String propertyName, Iterator iterator) throws MappingException {
		if ( iterator.hasNext() ) {
			String root = StringHelper.root( propertyName );
			while ( iterator.hasNext() ) {
				Property prop = (Property) iterator.next();
				if ( prop.getName().equals( root ) ) {
					return prop;
				}
			}
		}
		throw new MappingException( "property [" + propertyName + "] not found on entity [" + getEntityName() + "]" );
	}

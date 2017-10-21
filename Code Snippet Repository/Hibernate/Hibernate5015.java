	@Override
	public int getPropertyIndex(String name) {
		if ( PROPERTY_NAMES[0].equals( name ) ) {
			return 0;
		}
		else if ( PROPERTY_NAMES[1].equals( name ) ) {
			return 1;
		}

		throw new PropertyNotFoundException( "Unable to locate property named " + name + " on AnyType" );
	}

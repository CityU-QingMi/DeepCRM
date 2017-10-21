	public static Constructor getConstructor(Class clazz, Type[] types) throws PropertyNotFoundException {
		final Constructor[] candidates = clazz.getConstructors();
		Constructor constructor = null;
		int numberOfMatchingConstructors = 0;
		for ( final Constructor candidate : candidates ) {
			final Class[] params = candidate.getParameterTypes();
			if ( params.length == types.length ) {
				boolean found = true;
				for ( int j = 0; j < params.length; j++ ) {
					final boolean ok = types[j] == null || params[j].isAssignableFrom( types[j].getReturnedClass() ) || (
							types[j] instanceof PrimitiveType &&
									params[j] == ( (PrimitiveType) types[j] ).getPrimitiveClass()
					);
					if ( !ok ) {
						found = false;
						break;
					}
				}
				if ( found ) {
					numberOfMatchingConstructors ++;
					candidate.setAccessible( true );
					constructor = candidate;
				}
			}
		}

		if ( numberOfMatchingConstructors == 1 ) {
			return constructor;
		}
		throw new PropertyNotFoundException( "no appropriate constructor in class: " + clazz.getName() );

	}

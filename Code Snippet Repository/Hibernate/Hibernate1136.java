	private static void findAccessors(
			Class clazz,
			String[] getterNames,
			String[] setterNames,
			Class[] types,
			Method[] getters,
			Method[] setters) {
		final int length = types.length;
		if ( setterNames.length != length || getterNames.length != length ) {
			throw new BulkAccessorException( "bad number of accessors" );
		}

		final Class[] getParam = new Class[0];
		final Class[] setParam = new Class[1];
		for ( int i = 0; i < length; i++ ) {
			if ( getterNames[i] != null ) {
				final Method getter = findAccessor( clazz, getterNames[i], getParam, i );
				if ( getter.getReturnType() != types[i] ) {
					throw new BulkAccessorException( "wrong return type: " + getterNames[i], i );
				}

				getters[i] = getter;
			}

			if ( setterNames[i] != null ) {
				setParam[0] = types[i];
				setters[i] = findAccessor( clazz, setterNames[i], setParam, i );
			}
		}
	}

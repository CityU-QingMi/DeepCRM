	private Constructor<? extends EntityTuplizer> getProperConstructor(
			Class<? extends EntityTuplizer> clazz,
			Class[] constructorArgs) {
		Constructor<? extends EntityTuplizer> constructor = null;
		try {
			constructor = clazz.getDeclaredConstructor( constructorArgs );
			try {
				constructor.setAccessible( true );
			}
			catch ( SecurityException e ) {
				constructor = null;
			}
		}
		catch ( NoSuchMethodException ignore ) {
		}

		return constructor;
	}

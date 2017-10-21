	@SuppressWarnings("")
	private Constructor<? extends ComponentTuplizer> getProperConstructor(Class<? extends ComponentTuplizer> clazz) {
		Constructor<? extends ComponentTuplizer> constructor = null;
		try {
			constructor = clazz.getDeclaredConstructor( COMPONENT_TUP_CTOR_SIG );
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

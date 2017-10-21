	public static <T> T newInstance(Supplier<Constructor<T>> constructorSupplier, Object... args) {
		try {
			Constructor constructor  = constructorSupplier.get();
			constructor.setAccessible( true );
			return (T) constructor.newInstance( args );
		}
		catch ( IllegalAccessException | InstantiationException | InvocationTargetException e ) {
			throw new IllegalArgumentException("Constructor could not be called",  e );
		}
	}

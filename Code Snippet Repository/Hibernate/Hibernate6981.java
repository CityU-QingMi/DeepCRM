	public static <E> E createInstance(Class<E> entityClass) {
		Class<? extends E> loaded = new ByteBuddy()
				.subclass( entityClass )
				.method( ElementMatchers.named( "toString" ) )
				.intercept( FixedValue.value( "transformed" ) )
				.make()
				.load( entityClass.getClassLoader(), ClassLoadingStrategy.Default.INJECTION )
				.getLoaded();

		try {
			return loaded.newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException( "Unable to create new instance of " + entityClass.getSimpleName(), e );
		}
	}

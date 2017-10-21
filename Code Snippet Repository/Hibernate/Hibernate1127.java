	@SuppressWarnings("")
	private static Method findAccessor(Class clazz, String name, Class[] params, int index)
			throws BulkAccessorException {
		try {
			final Method method = clazz.getDeclaredMethod( name, params );
			if ( Modifier.isPrivate( method.getModifiers() ) ) {
				throw new BulkAccessorException( "private property", index );
			}

			return method;
		}
		catch (NoSuchMethodException e) {
			throw new BulkAccessorException( "cannot find an accessor", index );
		}
	}

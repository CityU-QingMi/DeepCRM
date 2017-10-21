	@Override
	@SuppressWarnings( {""})
	public <T> T unwrap(Class<T> unwrapType) {
		if ( XaConnectionProvider.class.isAssignableFrom( unwrapType ) ) {
			return (T) this;
		}
		else if ( ConnectionProvider.class.isAssignableFrom( unwrapType ) ||
				actualConnectionProvider.getClass().isAssignableFrom( unwrapType ) ) {
			return (T) getActualConnectionProvider();
		}
		else {
			throw new UnknownUnwrapTypeException( unwrapType );
		}
	}

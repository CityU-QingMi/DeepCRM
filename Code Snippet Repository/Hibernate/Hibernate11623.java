	@Override
	@SuppressWarnings( {""})
	public <T> T unwrap(Class<T> unwrapType) {
		if ( DualNodeConnectionProviderImpl.class.isAssignableFrom( unwrapType ) ) {
			return (T) this;
		}
		else if ( ConnectionProvider.class.isAssignableFrom( unwrapType ) ) {
			return (T) actualConnectionProvider;
		}
		else {
			throw new UnknownUnwrapTypeException( unwrapType );
		}
	}

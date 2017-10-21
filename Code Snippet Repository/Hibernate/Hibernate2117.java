	@Override
	@SuppressWarnings({""})
	public <T> T unwrap(Class<T> unwrapType) {
		if ( MultiTenantConnectionProvider.class.isAssignableFrom( unwrapType ) ) {
			return (T) this;
		}
		else if ( ConnectionProvider.class.isAssignableFrom( unwrapType ) ) {
			return (T) getAnyConnectionProvider();
		}
		else {
			throw new UnknownUnwrapTypeException( unwrapType );
		}
	}

	@Override
	@SuppressWarnings( {""})
	public <T> T unwrap(Class<T> unwrapType) {
		if ( MultiTenantConnectionProvider.class.isAssignableFrom( unwrapType ) ) {
			return (T) this;
		}
		else if ( DataSource.class.isAssignableFrom( unwrapType ) ) {
			return (T) selectAnyDataSource();
		}
		else {
			throw new UnknownUnwrapTypeException( unwrapType );
		}
	}

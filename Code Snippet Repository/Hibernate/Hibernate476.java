	@Override
	@SuppressWarnings({""})
	public <T> T unwrap(Class<T> unwrapType) {
		if ( ConnectionProvider.class.equals( unwrapType ) ||
				C3P0ConnectionProvider.class.isAssignableFrom( unwrapType ) ) {
			return (T) this;
		}
		else if ( DataSource.class.isAssignableFrom( unwrapType ) ) {
			return (T) ds;
		}
		else {
			throw new UnknownUnwrapTypeException( unwrapType );
		}
	}

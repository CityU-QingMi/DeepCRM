	@Override
	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		checkOpen();

		try {
			return byId( entityClass ).getReference( (Serializable) primaryKey );
		}
		catch ( MappingException | TypeMismatchException | ClassCastException e ) {
			throw exceptionConverter.convert( new IllegalArgumentException( e.getMessage(), e ) );
		}
		catch ( RuntimeException e ) {
			throw exceptionConverter.convert( e );
		}
	}

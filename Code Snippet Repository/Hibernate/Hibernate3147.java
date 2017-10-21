	@Override
	public void remove(Object entity) {
		checkOpen();

		try {
			delete( entity );
		}
		catch (MappingException e) {
			throw exceptionConverter.convert( new IllegalArgumentException( e.getMessage(), e ) );
		}
		catch ( RuntimeException e ) {
			//including HibernateException
			throw exceptionConverter.convert( e );
		}
	}

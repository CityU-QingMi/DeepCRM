	@Override
	@SuppressWarnings("")
	public <T> T unwrap(Class<T> cls) {
		if ( org.hibernate.Cache.class.isAssignableFrom( cls ) ) {
			return (T) this;
		}

		if ( RegionFactory.class.isAssignableFrom( cls ) ) {
			return (T) regionFactory;
		}

		throw new PersistenceException( "Hibernate cannot unwrap Cache as " + cls.getName() );
	}

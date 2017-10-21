	@Override
	public int getHashCode(Object x, SessionFactoryImplementor factory) {
		EntityPersister persister = getAssociatedEntityPersister( factory );
		if ( !persister.canExtractIdOutOfEntity() ) {
			return super.getHashCode( x );
		}

		final Serializable id;
		if ( x instanceof HibernateProxy ) {
			id = ( (HibernateProxy) x ).getHibernateLazyInitializer().getIdentifier();
		}
		else {
			final Class mappedClass = persister.getMappedClass();
			if ( mappedClass.isAssignableFrom( x.getClass() ) ) {
				id = persister.getIdentifier( x );
			}
			else {
				id = (Serializable) x;
			}
		}
		return persister.getIdentifierType().getHashCode( id, factory );
	}

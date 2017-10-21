	@Override
	public boolean isEqual(Object x, Object y, SessionFactoryImplementor factory) {
		// associations (many-to-one and one-to-one) can be null...
		if ( x == null || y == null ) {
			return x == y;
		}

		EntityPersister persister = getAssociatedEntityPersister( factory );
		if ( !persister.canExtractIdOutOfEntity() ) {
			return super.isEqual( x, y );
		}

		final Class mappedClass = persister.getMappedClass();
		Serializable xid;
		if ( x instanceof HibernateProxy ) {
			xid = ( (HibernateProxy) x ).getHibernateLazyInitializer()
					.getIdentifier();
		}
		else {
			if ( mappedClass.isAssignableFrom( x.getClass() ) ) {
				xid = persister.getIdentifier( x );
			}
			else {
				//JPA 2 case where @IdClass contains the id and not the associated entity
				xid = (Serializable) x;
			}
		}

		Serializable yid;
		if ( y instanceof HibernateProxy ) {
			yid = ( (HibernateProxy) y ).getHibernateLazyInitializer()
					.getIdentifier();
		}
		else {
			if ( mappedClass.isAssignableFrom( y.getClass() ) ) {
				yid = persister.getIdentifier( y );
			}
			else {
				//JPA 2 case where @IdClass contains the id and not the associated entity
				yid = (Serializable) y;
			}
		}

		return persister.getIdentifierType()
				.isEqual( xid, yid, factory );
	}

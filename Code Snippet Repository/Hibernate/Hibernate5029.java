	public Serializable getKeyOfOwner(Object owner, SharedSessionContractImplementor session) {
		
		EntityEntry entityEntry = session.getPersistenceContext().getEntry( owner );
		if ( entityEntry == null ) {
			// This just handles a particular case of component
			// projection, perhaps get rid of it and throw an exception
			return null;
		}
		
		if ( foreignKeyPropertyName == null ) {
			return entityEntry.getId();
		}
		else {
			// TODO: at the point where we are resolving collection references, we don't
			// know if the uk value has been resolved (depends if it was earlier or
			// later in the mapping document) - now, we could try and use e.getStatus()
			// to decide to semiResolve(), trouble is that initializeEntity() reuses
			// the same array for resolved and hydrated values
			Object id;
			if ( entityEntry.getLoadedState() != null ) {
				id = entityEntry.getLoadedValue( foreignKeyPropertyName );
			}
			else {
				id = entityEntry.getPersister().getPropertyValue( owner, foreignKeyPropertyName );
			}

			// NOTE VERY HACKISH WORKAROUND!!
			// TODO: Fix this so it will work for non-POJO entity mode
			Type keyType = getPersister( session ).getKeyType();
			if ( !keyType.getReturnedClass().isInstance( id ) ) {
				id = keyType.semiResolve(
						entityEntry.getLoadedValue( foreignKeyPropertyName ),
						session,
						owner 
				);
			}

			return (Serializable) id;
		}
	}

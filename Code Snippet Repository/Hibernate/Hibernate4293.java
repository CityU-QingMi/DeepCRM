	private Object getReplacement() {
		final SharedSessionContractImplementor session = getSession();
		if ( isUninitialized() && session != null && session.isOpen() ) {
			final EntityKey key = session.generateEntityKey(
					getIdentifier(),
					session.getFactory().getMetamodel().entityPersister( getEntityName() )
			);
			final Object entity = session.getPersistenceContext().getEntity( key );
			if ( entity != null ) {
				setImplementation( entity );
			}
		}

		if ( isUninitialized() ) {
			if ( replacement == null ) {
				replacement = serializableProxy();
			}
			return replacement;
		}
		else {
			return getTarget();
		}

	}

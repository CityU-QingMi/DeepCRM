	public static Serializable getEntityIdentifierIfNotUnsaved(
			final String entityName,
			final Object object,
			final SharedSessionContractImplementor session) throws TransientObjectException {
		if ( object == null ) {
			return null;
		}
		else {
			Serializable id = session.getContextEntityIdentifier( object );
			if ( id == null ) {
				// context-entity-identifier returns null explicitly if the entity
				// is not associated with the persistence context; so make some
				// deeper checks...
				if ( isTransient( entityName, object, Boolean.FALSE, session ) ) {
					throw new TransientObjectException(
							"object references an unsaved transient instance - save the transient instance before flushing: " +
									(entityName == null ? session.guessEntityName( object ) : entityName)
					);
				}
				id = session.getEntityPersister( entityName, object ).getIdentifier( object, session );
			}
			return id;
		}
	}

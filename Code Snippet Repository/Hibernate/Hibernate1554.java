	public static void identityRemove(
			Collection list,
			Object entityInstance,
			String entityName,
			SharedSessionContractImplementor session) {

		if ( entityInstance != null && ForeignKeys.isNotTransient( entityName, entityInstance, null, session ) ) {
			final EntityPersister entityPersister = session.getFactory().getEntityPersister( entityName );
			final Type idType = entityPersister.getIdentifierType();

			final Serializable idOfCurrent = ForeignKeys.getEntityIdentifierIfNotUnsaved( entityName, entityInstance, session );
			final Iterator itr = list.iterator();
			while ( itr.hasNext() ) {
				final Serializable idOfOld = ForeignKeys.getEntityIdentifierIfNotUnsaved( entityName, itr.next(), session );
				if ( idType.isEqual( idOfCurrent, idOfOld, session.getFactory() ) ) {
					itr.remove();
					break;
				}
			}

		}
	}

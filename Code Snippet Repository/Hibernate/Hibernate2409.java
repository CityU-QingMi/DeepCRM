	public void checkId(Object object, EntityPersister persister, Serializable id, SessionImplementor session)
			throws HibernateException {

		if ( id != null && id instanceof DelayedPostInsertIdentifier ) {
			// this is a situation where the entity id is assigned by a post-insert generator
			// and was saved outside the transaction forcing it to be delayed
			return;
		}

		if ( persister.canExtractIdOutOfEntity() ) {

			Serializable oid = persister.getIdentifier( object, session );
			if ( id == null ) {
				throw new AssertionFailure( "null id in " + persister.getEntityName() + " entry (don't flush the Session after an exception occurs)" );
			}
			if ( !persister.getIdentifierType().isEqual( id, oid, session.getFactory() ) ) {
				throw new HibernateException(
						"identifier of an instance of " + persister.getEntityName() + " was altered from "
								+ id + " to " + oid
				);
			}
		}

	}

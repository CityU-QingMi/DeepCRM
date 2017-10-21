	final Object processArrayOrNewCollection(Object collection, CollectionType collectionType)
			throws HibernateException {

		final SessionImplementor session = getSession();

		if ( collection == null ) {
			//do nothing
			return null;
		}
		else {
			CollectionPersister persister = session.getFactory().getCollectionPersister( collectionType.getRole() );

			final PersistenceContext persistenceContext = session.getPersistenceContext();
			//TODO: move into collection type, so we can use polymorphism!
			if ( collectionType.hasHolder() ) {

				if ( collection == CollectionType.UNFETCHED_COLLECTION ) {
					return null;
				}

				PersistentCollection ah = persistenceContext.getCollectionHolder( collection );
				if ( ah == null ) {
					ah = collectionType.wrap( session, collection );
					persistenceContext.addNewCollection( persister, ah );
					persistenceContext.addCollectionHolder( ah );
				}
				return null;
			}
			else {

				PersistentCollection persistentCollection = collectionType.wrap( session, collection );
				persistenceContext.addNewCollection( persister, persistentCollection );

				if ( LOG.isTraceEnabled() ) {
					LOG.tracev( "Wrapped collection in role: {0}", collectionType.getRole() );
				}

				return persistentCollection; //Force a substitution!

			}

		}

	}

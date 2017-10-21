	@Override
	public Object processCollection(Object collection, CollectionType type) throws HibernateException {
		if ( collection == null ) {
			return null;
		}

		final SessionImplementor session = getSession();
		final CollectionPersister persister = session.getFactory().getCollectionPersister( type.getRole() );

		if ( collection instanceof PersistentCollection ) {
			final PersistentCollection persistentCollection = (PersistentCollection) collection;
			if ( persistentCollection.setCurrentSession( session ) ) {
				if ( isOwnerUnchanged( persistentCollection, persister, extractCollectionKeyFromOwner( persister ) ) ) {
					// a "detached" collection that originally belonged to the same entity
					if ( persistentCollection.isDirty() ) {
						throw new HibernateException( "reassociated object has dirty collection" );
					}
					reattachCollection( persistentCollection, type );
				}
				else {
					// a "detached" collection that belonged to a different entity
					throw new HibernateException( "reassociated object has dirty collection reference" );
				}
			}
			else {
				// a collection loaded in the current session
				// can not possibly be the collection belonging
				// to the entity passed to update()
				throw new HibernateException( "reassociated object has dirty collection reference" );
			}
		}
		else {
			// brand new collection
			//TODO: or an array!! we can't lock objects with arrays now??
			throw new HibernateException( "reassociated object has dirty collection reference (or an array)" );
		}

		return null;
	}

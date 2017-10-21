	@Override
	public Object[] getNaturalIdSnapshot(Serializable id, EntityPersister persister) throws HibernateException {
		if ( !persister.hasNaturalIdentifier() ) {
			return null;
		}

		persister = locateProperPersister( persister );

		// let's first see if it is part of the natural id cache...
		final Object[] cachedValue = naturalIdHelper.findCachedNaturalId( persister, id );
		if ( cachedValue != null ) {
			return cachedValue;
		}

		// check to see if the natural id is mutable/immutable
		if ( persister.getEntityMetamodel().hasImmutableNaturalId() ) {
			// an immutable natural-id is not retrieved during a normal database-snapshot operation...
			final Object[] dbValue = persister.getNaturalIdentifierSnapshot( id, session );
			naturalIdHelper.cacheNaturalIdCrossReferenceFromLoad(
					persister,
					id,
					dbValue
			);
			return dbValue;
		}
		else {
			// for a mutable natural there is a likelihood that the the information will already be
			// snapshot-cached.
			final int[] props = persister.getNaturalIdentifierProperties();
			final Object[] entitySnapshot = getDatabaseSnapshot( id, persister );
			if ( entitySnapshot == NO_ROW || entitySnapshot == null ) {
				return null;
			}

			final Object[] naturalIdSnapshotSubSet = new Object[ props.length ];
			for ( int i = 0; i < props.length; i++ ) {
				naturalIdSnapshotSubSet[i] = entitySnapshot[ props[i] ];
			}
			naturalIdHelper.cacheNaturalIdCrossReferenceFromLoad(
					persister,
					id,
					naturalIdSnapshotSubSet
			);
			return naturalIdSnapshotSubSet;
		}
	}

	@Override
	public Object replace(
			Object original,
			Object target,
			SharedSessionContractImplementor session,
			Object owner,
			Map copyCache) throws HibernateException {
		if ( original == null ) {
			return null;
		}
		Object cached = copyCache.get( original );
		if ( cached == null ) {
			// Avoid creation of invalid managed -> managed mapping in copyCache when traversing
			// cascade loop (@OneToMany(cascade=ALL) with associated @ManyToOne(cascade=ALL)) in entity graph
			if ( copyCache.containsValue( original ) ) {
				cached = original;
			}
		}
		if ( cached != null ) {
			return cached;
		}
		else {
			if ( original == target ) {
				return target;
			}
			if ( session.getContextEntityIdentifier( original ) == null &&
					ForeignKeys.isTransient( associatedEntityName, original, Boolean.FALSE, session ) ) {
				final Object copy = session.getEntityPersister( associatedEntityName, original )
						.instantiate( null, session );
				copyCache.put( original, copy );
				return copy;
			}
			else {
				Object id = getIdentifier( original, session );
				if ( id == null ) {
					throw new AssertionFailure(
							"non-transient entity has a null id: " + original.getClass()
									.getName()
					);
				}
				id = getIdentifierOrUniqueKeyType( session.getFactory() )
						.replace( id, null, session, owner, copyCache );
				return resolve( id, session, owner );
			}
		}
	}

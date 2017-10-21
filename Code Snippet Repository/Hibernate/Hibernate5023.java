	@Override
	public Object replace(
			final Object original,
			final Object target,
			final SharedSessionContractImplementor session,
			final Object owner,
			final Map copyCache) throws HibernateException {
		if ( original == null ) {
			return null;
		}
		if ( !Hibernate.isInitialized( original ) ) {
			if ( ( (PersistentCollection) original ).hasQueuedOperations() ) {
				final AbstractPersistentCollection pc = (AbstractPersistentCollection) original;
				pc.replaceQueuedOperationValues( getPersister( session ), copyCache );
			}
			return target;
		}

		// for a null target, or a target which is the same as the original, we
		// need to put the merged elements in a new collection
		Object result = target == null || target == original ? instantiateResult( original ) : target;
		
		//for arrays, replaceElements() may return a different reference, since
		//the array length might not match
		result = replaceElements( original, result, owner, copyCache, session );

		if ( original == target ) {
			// get the elements back into the target making sure to handle dirty flag
			boolean wasClean = PersistentCollection.class.isInstance( target ) && !( ( PersistentCollection ) target ).isDirty();
			//TODO: this is a little inefficient, don't need to do a whole
			//      deep replaceElements() call
			replaceElements( result, target, owner, copyCache, session );
			if ( wasClean ) {
				( ( PersistentCollection ) target ).clearDirty();
			}
			result = target;
		}

		return result;
	}

	public Object replaceElements(
			Object original,
			Object target,
			Object owner,
			Map copyCache,
			SharedSessionContractImplementor session) {
		// TODO: does not work for EntityMode.DOM4J yet!
		java.util.Collection result = ( java.util.Collection ) target;
		result.clear();

		// copy elements into newly empty target collection
		Type elemType = getElementType( session.getFactory() );
		Iterator iter = ( (java.util.Collection) original ).iterator();
		while ( iter.hasNext() ) {
			result.add( elemType.replace( iter.next(), null, session, owner, copyCache ) );
		}

		// if the original is a PersistentCollection, and that original
		// was not flagged as dirty, then reset the target's dirty flag
		// here after the copy operation.
		// </p>
		// One thing to be careful of here is a "bare" original collection
		// in which case we should never ever ever reset the dirty flag
		// on the target because we simply do not know...
		if ( original instanceof PersistentCollection ) {
			if ( result instanceof PersistentCollection ) {
				final PersistentCollection originalPersistentCollection = (PersistentCollection) original;
				final PersistentCollection resultPersistentCollection = (PersistentCollection) result;

				preserveSnapshot( originalPersistentCollection, resultPersistentCollection, elemType, owner, copyCache, session );

				if ( ! originalPersistentCollection.isDirty() ) {
					resultPersistentCollection.clearDirty();
				}
			}
		}

		return result;
	}

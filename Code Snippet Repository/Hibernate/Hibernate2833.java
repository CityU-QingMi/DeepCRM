	public boolean needsIdTable(PersistentClass entityBinding) {
		// need id table if the entity has secondary tables (joins)
		if ( entityBinding.getJoinClosureSpan() > 0 ) {
			return true;
		}

		// need an id table if the entity is part of either a JOINED or UNION inheritance
		// hierarchy.  We do not allow inheritance strategy mixing, so work on that assumption
		// here...
		final RootClass rootEntityBinding = entityBinding.getRootClass();
		final Iterator itr = rootEntityBinding.getSubclassIterator();
		if ( itr.hasNext() ) {
			final Subclass subclassEntityBinding = (Subclass) itr.next();
			if ( subclassEntityBinding instanceof JoinedSubclass || subclassEntityBinding instanceof UnionSubclass ) {
				return true;
			}
		}

		return false;
	}

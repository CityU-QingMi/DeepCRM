	public E remove(int index) {
		// removals are generally safe in regards to sorting...

		final E e = executables.remove( index );

		// If the executable being removed defined query spaces we need to recalculate the overall query spaces for
		// this list.  The problem is that we don't know how many other executable instances in the list also
		// contributed those query spaces as well.
		//
		// An alternative here is to use a "multiset" which is a specialized set that keeps a reference count
		// associated to each entry.  But that is likely overkill here.
		if ( e.getPropertySpaces() != null && e.getPropertySpaces().length > 0 ) {
			querySpaces = null;
		}
		return e;
	}

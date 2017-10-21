	public boolean add(E executable) {
		final E previousLast = sorter != null || executables.isEmpty() ? null : executables.get( executables.size() - 1 );
		boolean added = executables.add( executable );

		if ( !added ) {
			return false;
		}

		// if it was sorted before the addition, then check if the addition invalidated the sorting
		if ( sorted ) {
			if ( sorter != null ) {
				// we don't have intrinsic insight into the sorter's algorithm, so invalidate sorting
				sorted = false;
			}
			else {
				// otherwise, we added to the end of the list.  So check the comparison between the incoming
				// executable and the one previously at the end of the list using the Comparable contract
				if ( previousLast != null && previousLast.compareTo( executable ) > 0 ) {
					sorted = false;
				}
			}
		}

		Serializable[] querySpaces = executable.getPropertySpaces();
		if ( this.querySpaces != null && querySpaces != null ) {
			Collections.addAll( this.querySpaces, querySpaces );
		}

		return added;
	}

	public void removeLastN(int n) {
		if ( n > 0 ) {
			int size = executables.size();
			for ( Executable e : executables.subList( size - n, size ) ) {
				if ( e.getPropertySpaces() != null && e.getPropertySpaces().length > 0 ) {
					// querySpaces could now be incorrect
					querySpaces = null;
					break;
				}
			}
			executables.subList( size - n, size ).clear();
		}
	}

	@SuppressWarnings("")
	public int occurrences(Object o) {
		read();
		final Iterator itr = bag.iterator();
		int result = 0;
		while ( itr.hasNext() ) {
			if ( o.equals( itr.next() ) ) {
				result++;
			}
		}
		return result;
	}

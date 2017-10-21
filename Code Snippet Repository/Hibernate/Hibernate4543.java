	@Override
	@SuppressWarnings("")
	public T next() {
		Object[] next = scrollableResults.get();
		if ( next.length == 1 ) {
			return (T) next[0];
		}
		else {
			return (T) next;
		}
	}

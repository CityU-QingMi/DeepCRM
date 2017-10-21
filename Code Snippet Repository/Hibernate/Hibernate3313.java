	protected void updateCurrentIterator() {
		if ( currentIterator == null ) {
			if( wrappedIterators.length == 0  ) {
				currentIterator = Collections.<T>emptyList().iterator();
			}
			else {
				currentIterator = wrappedIterators[0];
			}
			// set last used iterator here, in case the user calls remove
			// before calling hasNext() or next() (although they shouldn't)
			lastUsedIterator = currentIterator;
		}

		while (! currentIterator.hasNext() && currentIteratorIndex < wrappedIterators.length - 1) {
			currentIteratorIndex++;
			currentIterator = wrappedIterators[currentIteratorIndex];
		}
	}

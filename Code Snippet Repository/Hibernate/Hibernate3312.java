		@SuppressWarnings( {""})
		protected void updateCurrentIterator() {

			if ( currentIterator == null) {
				if( iterables.size() == 0  ) {
					currentIterator = EmptyIterator.INSTANCE;
				}
				else {
					currentIterator = iterables.get( 0 ).iterator();
				}
				// set last used iterator here, in case the user calls remove
				// before calling hasNext() or next() (although they shouldn't)
				lastUsedIterator = currentIterator;
			}

			while (! currentIterator.hasNext() && currentIterableIndex < iterables.size() - 1) {
				currentIterableIndex++;
				currentIterator = iterables.get( currentIterableIndex ).iterator();
			}
		}

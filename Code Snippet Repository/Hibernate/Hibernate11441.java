	protected void runInvalidation(boolean inTransaction) {
		// If we're running inside a transaction, we need to remove elements one-by-one
		// to clean the context as well (cache.clear() does not do that).
		// When we don't have transaction, we can do a clear operation (since we don't
		// case about context) and can't do the one-by-one remove: remove() on tx cache
		// requires transactional context.
		if ( inTransaction ) {
			log.tracef( "Transaction, clearing one element at the time" );
			Caches.removeAll( localAndSkipLoadCache );
		}
		else {
			log.tracef( "Non-transactional, clear in one go" );
			localAndSkipLoadCache.clear();
		}
	}

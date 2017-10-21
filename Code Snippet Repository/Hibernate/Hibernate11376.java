	@Override
	@SuppressWarnings("")
	public boolean putFromLoad(SharedSessionContractImplementor session, Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride)
			throws CacheException {
		if ( !region.checkValid() ) {
			if ( TRACE_ENABLED ) {
				log.tracef( "Region %s not valid", region.getName() );
			}
			return false;
		}

		// In theory, since putForExternalRead is already as minimal as it can
		// get, we shouldn't be need this check. However, without the check and
		// without https://issues.jboss.org/browse/ISPN-1986, it's impossible to
		// know whether the put actually occurred. Knowing this is crucial so
		// that Hibernate can expose accurate statistics.
		if ( minimalPutOverride && cache.containsKey( key ) ) {
			return false;
		}

		PutFromLoadValidator.Lock lock = putValidator.acquirePutFromLoadLock(session, key, txTimestamp);
		if ( lock == null) {
			if ( TRACE_ENABLED ) {
				log.tracef( "Put from load lock not acquired for key %s", key );
			}
			return false;
		}

		try {
			writeCache.putForExternalRead( key, value );
		}
		finally {
			putValidator.releasePutFromLoadLock( key, lock);
		}

		return true;
	}

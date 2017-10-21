		final Object newKeyReference(
				K key, ReferenceType keyType,
				ReferenceQueue<Object> refQueue) {
			if ( keyType == ReferenceType.WEAK ) {
				return new WeakKeyReference<K>( key, hash, refQueue );
			}
			if ( keyType == ReferenceType.SOFT ) {
				return new SoftKeyReference<K>( key, hash, refQueue );
			}

			return key;
		}

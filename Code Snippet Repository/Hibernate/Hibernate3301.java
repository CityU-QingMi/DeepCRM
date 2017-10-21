		final Object newValueReference(
				V value, ReferenceType valueType,
				ReferenceQueue<Object> refQueue) {
			if ( valueType == ReferenceType.WEAK ) {
				return new WeakValueReference<V>( value, keyRef, hash, refQueue );
			}
			if ( valueType == ReferenceType.SOFT ) {
				return new SoftValueReference<V>( value, keyRef, hash, refQueue );
			}

			return value;
		}

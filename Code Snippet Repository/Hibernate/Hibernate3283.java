		public void hit(Set<HashEntry<K, V>> evicted) {
			switch ( state ) {
				case LIR_RESIDENT:
					hotHit( evicted );
					break;
				case HIR_RESIDENT:
					coldHit( evicted );
					break;
				case HIR_NONRESIDENT:
					throw new IllegalStateException( "Can't hit a non-resident entry!" );
				default:
					throw new AssertionError( "Hit with unknown status: " + state );
			}
		}

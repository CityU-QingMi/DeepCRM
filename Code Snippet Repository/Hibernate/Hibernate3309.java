		@Override
		public int hashCode() {
			if ( this.hash == 0 ) {
				//We consider "zero" as non-initialized value
				final int newHash = System.identityHashCode( key );
				if ( newHash == 0 ) {
					//So make sure we don't store zeros as it would trigger initialization again:
					//any value is fine as long as we're deterministic.
					this.hash = -1;
					return -1;
				}
				else {
					this.hash = newHash;
					return newHash;
				}
			}
			return hash;
		}

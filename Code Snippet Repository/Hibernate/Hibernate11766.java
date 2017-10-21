		@Override
		@SuppressWarnings("")
		public boolean equals(Object o) {
			if ( o == this ) {
				return true;
			}
			else if ( o instanceof Lock ) {
				return (lockId == ((Lock)o ).lockId) && sourceUuid.equals( ( (Lock) o ).sourceUuid )
						&& (multiplicity == ((Lock) o).multiplicity);
			}
			else {
				return false;
			}
		}

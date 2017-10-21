		@Override
		public boolean equals(Object obj) {
			if ( this == obj ) {
				return true;
			}
			if ( obj == null ) {
				return false;
			}
			if ( getClass() != obj.getClass() ) {
				return false;
			}

			final CachedNaturalId other = (CachedNaturalId) obj;
			return persister.equals( other.persister ) && isSame( other.values );
		}

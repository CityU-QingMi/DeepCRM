		@Override
		public boolean equals(Object o) {
			// IMPL NOTE : nulls are considered equal for keys and values according to Map.Entry contract
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}
			EntryAdapter that = (EntryAdapter) o;

			// make sure we have the same types...
			return ( key == null ? that.key == null : key.equals( that.key ) )
					&& ( value == null ? that.value == null : value.equals( that.value ) );

		}

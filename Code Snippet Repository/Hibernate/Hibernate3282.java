		@Override
		public boolean equals(Object o) {
			// HashEntry is internal class, never leaks out of CHM, hence slight optimization
			if ( this == o ) {
				return true;
			}
			if ( o == null ) {
				return false;
			}
			HashEntry<?, ?> other = (HashEntry<?, ?>) o;
			return hash == other.hash && key.equals( other.key );
		}

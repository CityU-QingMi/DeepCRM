		public static <T extends Comparable<T>> int compare(T first, T second) {
			if ( first == null ) {
				if ( second == null ) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else {
				if ( second == null ) {
					return -1;
				}
				else {
					return first.compareTo( second );
				}
			}
		}

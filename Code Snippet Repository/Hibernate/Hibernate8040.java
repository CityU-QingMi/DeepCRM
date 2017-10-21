	public int compareTo(Object o) {
		int otherOrdinal = ( ( Classification ) o ).ordinal;
		if ( ordinal == otherOrdinal ) {
			return 0;
		}
		else if ( ordinal > otherOrdinal ) {
			return 1;
		}
		else {
			return -1;
		}
	}

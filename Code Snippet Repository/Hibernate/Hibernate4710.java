	private static Ordering resolve(String text) {
		if ( Ordering.ASCENDING.name.equals( text ) ) {
			return Ordering.ASCENDING;
		}
		else if ( Ordering.DESCENDING.name.equals( text ) ) {
			return Ordering.DESCENDING;
		}
		else {
			throw new IllegalStateException( "Unknown ordering [" + text + "]" );
		}
	}

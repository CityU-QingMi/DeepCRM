	private String getErrorString() {
		final StringBuilder buf = new StringBuilder();
		final Iterator<String> iterator = errorList.iterator();
		while ( iterator.hasNext() ) {
			buf.append( iterator.next() );
			if ( iterator.hasNext() ) {
				buf.append( "\n" );
			}

		}
		return buf.toString();
	}

	public static void append(StringBuilder sb, Iterator<String> contents, String separator) {
		boolean isFirst = true;
		while ( contents.hasNext() ) {
			if ( !isFirst ) {
				sb.append( separator );
			}
			sb.append( contents.next() );
			isFirst = false;
		}
	}

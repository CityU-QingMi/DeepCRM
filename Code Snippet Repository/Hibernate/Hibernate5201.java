	private static String extractString(Reader characterStream, long start, int length) {
		if ( length == 0 ) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder( length );
		try {
			long skipped = characterStream.skip( start );
			if ( skipped != start ) {
				throw new HibernateException( "Unable to skip needed bytes" );
			}
			final int bufferSize = getSuggestedBufferSize( length );
			char[] buffer = new char[bufferSize];
			int charsRead = 0;
			while ( true ) {
				int amountRead = characterStream.read( buffer, 0, bufferSize );
				if ( amountRead == -1 ) {
					break;
				}
				stringBuilder.append( buffer, 0, amountRead );
				if ( amountRead < bufferSize ) {
					// we have read up to the end of stream
					break;
				}
				charsRead += amountRead;
				if ( charsRead >= length ) {
					break;
				}
			}
		}
		catch ( IOException ioe ) {
			throw new HibernateException( "IOException occurred reading a binary value", ioe );
		}
		return stringBuilder.toString();
	}

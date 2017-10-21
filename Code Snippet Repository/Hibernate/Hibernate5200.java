	public static String extractString(Reader reader, int lengthHint) {
		// read the Reader contents into a buffer and return the complete string
		final int bufferSize = getSuggestedBufferSize( lengthHint );
		final StringBuilder stringBuilder = new StringBuilder( bufferSize );
		try {
			char[] buffer = new char[bufferSize];
			while (true) {
				int amountRead = reader.read( buffer, 0, bufferSize );
				if ( amountRead == -1 ) {
					break;
				}
				stringBuilder.append( buffer, 0, amountRead );
			}
		}
		catch ( IOException ioe ) {
			throw new HibernateException( "IOException occurred reading text", ioe );
		}
		finally {
			try {
				reader.close();
			}
			catch (IOException e) {
				LOG.unableToCloseStream( e );
			}
		}
		return stringBuilder.toString();
	}

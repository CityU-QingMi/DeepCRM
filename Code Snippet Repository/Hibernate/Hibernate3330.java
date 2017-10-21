	public static long copy(InputStream from, OutputStream into) {
		try {
			long totalRead = 0;
			while ( true ) {
				synchronized ( BUFFER ) {
					int amountRead = from.read( BUFFER );
					if ( amountRead == -1 ) {
						break;
					}
					into.write( BUFFER, 0, amountRead );
					totalRead += amountRead;
					if ( amountRead < BUFFER_SIZE ) {
						// should mean there is no more data in the stream, no need for next read
						break;
					}
				}
			}
			return totalRead;
		}
		catch (IOException e ) {
			throw new HibernateException( "Unable to copy stream content", e );
		}
	}

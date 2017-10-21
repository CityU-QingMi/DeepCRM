	public static String extractString(final Clob value) {
		try {
			final Reader characterStream = value.getCharacterStream();
			final long length = determineLengthForBufferSizing( value );
			return length > Integer.MAX_VALUE
					? extractString( characterStream, Integer.MAX_VALUE )
					: extractString( characterStream, (int) length );
		}
		catch ( SQLException e ) {
			throw new HibernateException( "Unable to access lob stream", e );
		}
	}

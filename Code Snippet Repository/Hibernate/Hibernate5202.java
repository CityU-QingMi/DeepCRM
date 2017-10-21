	public static byte[] extractBytes(InputStream inputStream) {
		if ( BinaryStream.class.isInstance( inputStream ) ) {
			return ( (BinaryStream ) inputStream ).getBytes();
		}

		// read the stream contents into a buffer and return the complete byte[]
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(BUFFER_SIZE);
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			while (true) {
				int amountRead = inputStream.read( buffer );
				if ( amountRead == -1 ) {
					break;
				}
				outputStream.write( buffer, 0, amountRead );
			}
		}
		catch ( IOException ioe ) {
			throw new HibernateException( "IOException occurred reading a binary value", ioe );
		}
		finally {
			try {
				inputStream.close();
			}
			catch ( IOException e ) {
				LOG.unableToCloseInputStream( e );
			}
			try {
				outputStream.close();
			}
			catch ( IOException e ) {
				LOG.unableToCloseOutputStream( e );
			}
		}
		return outputStream.toByteArray();
	}

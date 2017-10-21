	public static Binding doBind(Binder binder, InputStream inputStream, Origin origin, boolean autoClose) {
		try {
			return binder.bind( inputStream, origin );
		}
		catch ( Exception e ) {
			throw new InvalidMappingException( origin, e );
		}
		finally {
			if ( autoClose ) {
				try {
					inputStream.close();
				}
				catch ( IOException ignore ) {
					log.trace( "Was unable to close input stream" );
				}
			}
		}
	}

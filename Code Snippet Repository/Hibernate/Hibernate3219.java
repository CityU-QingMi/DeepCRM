	@SuppressWarnings("")
	public static <T> T doDeserialize(
			InputStream inputStream,
			ClassLoader loader,
			ClassLoader fallbackLoader1,
			ClassLoader fallbackLoader2) throws SerializationException {
		if ( inputStream == null ) {
			throw new IllegalArgumentException( "The InputStream must not be null" );
		}

		LOG.trace( "Starting deserialization of object" );

		try {
			CustomObjectInputStream in = new CustomObjectInputStream(
					inputStream,
					loader,
					fallbackLoader1,
					fallbackLoader2
			);
			try {
				return (T) in.readObject();
			}
			catch (ClassNotFoundException e) {
				throw new SerializationException( "could not deserialize", e );
			}
			catch (IOException e) {
				throw new SerializationException( "could not deserialize", e );
			}
			finally {
				try {
					in.close();
				}
				catch (IOException ignore) {
					// ignore
				}
			}
		}
		catch (IOException e) {
			throw new SerializationException( "could not deserialize", e );
		}
	}

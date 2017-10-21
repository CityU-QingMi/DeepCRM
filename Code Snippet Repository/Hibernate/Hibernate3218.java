	public static void serialize(Serializable obj, OutputStream outputStream) throws SerializationException {
		if ( outputStream == null ) {
			throw new IllegalArgumentException( "The OutputStream must not be null" );
		}

		if ( LOG.isTraceEnabled() ) {
			if ( Hibernate.isInitialized( obj ) ) {
				LOG.tracev( "Starting serialization of object [{0}]", obj );
			}
			else {
				LOG.trace( "Starting serialization of [uninitialized proxy]" );
			}
		}

		ObjectOutputStream out = null;
		try {
			// stream closed in the finally
			out = new ObjectOutputStream( outputStream );
			out.writeObject( obj );

		}
		catch (IOException ex) {
			throw new SerializationException( "could not serialize", ex );
		}
		finally {
			try {
				if ( out != null ) {
					out.close();
				}
			}
			catch (IOException ignored) {
			}
		}
	}

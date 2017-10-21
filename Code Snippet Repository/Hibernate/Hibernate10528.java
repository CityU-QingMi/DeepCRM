	private InputStream convertObjectToInputStream(Object value) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream( byteArrayOutputStream );
			objectOutputStream.writeObject( value );
			objectOutputStream.flush();
			return new ByteArrayInputStream( byteArrayOutputStream.toByteArray() );
		}
		catch (IOException e) {
			throw new RuntimeException( e );
		}
		finally {
			closeQuietly( objectOutputStream );
		}
	}

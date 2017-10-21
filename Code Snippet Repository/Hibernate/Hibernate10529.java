	private Object convertInputStreamToObject(InputStream inputStream) {
		ObjectInputStream objectInputStream = null;
		try {
			objectInputStream = new ObjectInputStream( inputStream );
			return objectInputStream.readObject();
		}
		catch (Exception e) {
			throw new RuntimeException( e );
		}
		finally {
			closeQuietly( objectInputStream );
		}
	}

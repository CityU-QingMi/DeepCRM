	@SuppressWarnings({""})
	public static <T> T serializeDeserialize(T o) throws Exception {
		if ( o == null ) {
			return null;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream( baos );
		oos.writeObject( o );
		byte[] buffer = baos.toByteArray();
		baos.close();

		ByteArrayInputStream bais = new ByteArrayInputStream( buffer );
		ObjectInputStream ois = new ObjectInputStream( bais );
		return (T) ois.readObject();
	}

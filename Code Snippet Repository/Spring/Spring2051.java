	private Object serializeValue(SerializationDelegate serialization, Object storeValue) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			serialization.serialize(storeValue, out);
			return out.toByteArray();
		}
		finally {
			out.close();
		}
	}

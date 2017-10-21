	private byte[] serialize(Serializable uniqueId) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		try (ObjectOutputStream out = new ObjectOutputStream(byteStream)) {
			out.writeObject(uniqueId);
		}
		catch (IOException e) {
			return uniqueId.toString().getBytes(CHARSET);
		}
		return byteStream.toByteArray();
	}

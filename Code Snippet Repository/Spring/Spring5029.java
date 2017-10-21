	private ResolvableType testSerialization(ResolvableType type) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(type);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		ResolvableType read = (ResolvableType) ois.readObject();
		assertThat(read, equalTo(type));
		assertThat(read.getType(), equalTo(type.getType()));
		assertThat(read.resolve(), equalTo((Class) type.resolve()));
		return read;
	}

	public static CollectionEntry deserialize(
			ObjectInputStream ois,
			SessionImplementor session) throws IOException, ClassNotFoundException {
		return new CollectionEntry(
				(String) ois.readObject(),
				(Serializable) ois.readObject(),
				(Serializable) ois.readObject(),
				(session == null ? null : session.getFactory())
		);
	}

	public static CollectionKey deserialize(
			ObjectInputStream ois,
			SessionImplementor session) throws IOException, ClassNotFoundException {
		return new CollectionKey(
				(String) ois.readObject(),
				(Serializable) ois.readObject(),
				(Type) ois.readObject(),
				EntityMode.parse( (String) ois.readObject() ),
				(session == null ? null : session.getFactory())
		);
	}

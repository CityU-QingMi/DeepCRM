	public static EntityUniqueKey deserialize(
			ObjectInputStream ois,
			SessionImplementor session) throws IOException, ClassNotFoundException {
		return new EntityUniqueKey(
				(String) ois.readObject(),
				(String) ois.readObject(),
				ois.readObject(),
				(Type) ois.readObject(),
				(EntityMode) ois.readObject(),
				session.getFactory()
		);
	}

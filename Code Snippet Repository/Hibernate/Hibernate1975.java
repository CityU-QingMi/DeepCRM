	public static EntityEntry deserialize(
			ObjectInputStream ois,
			PersistenceContext persistenceContext) throws IOException, ClassNotFoundException {
		String previousStatusString;
		return new MutableEntityEntry(
				persistenceContext.getSession().getFactory(),
				(String) ois.readObject(),
				(Serializable) ois.readObject(),
				Status.valueOf( (String) ois.readObject() ),
				( previousStatusString = (String) ois.readObject() ).length() == 0
						? null
						: Status.valueOf( previousStatusString ),
				(Object[]) ois.readObject(),
				(Object[]) ois.readObject(),
				ois.readObject(),
				LockMode.valueOf( (String) ois.readObject() ),
				ois.readBoolean(),
				ois.readBoolean(),
				persistenceContext
		);
	}

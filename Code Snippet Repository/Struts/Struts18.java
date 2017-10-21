	public Serializable create(IdEntity object) throws CreateException {
		if (object == null) {
			throw new CreateException("Either given class or object was null");
		}
		if (object.getId() == null) {
			throw new CreateException("Cannot store object with null id");
		}
		if (get(object.getClass(), object.getId()) != null) {
			throw new DuplicateKeyException("Object with this id already exists.");
		}
		return intStore(object.getClass(), object).getId();
	}

	public Serializable merge(IdEntity object) throws StorageException {
		if (object == null) {
			throw new StorageException("Cannot merge null object");
		}
		if (object.getId() == null || get(object.getClass(), object.getId()) == null) {
			return create(object);
		} else {
			return update(object).getId();
		}
	}

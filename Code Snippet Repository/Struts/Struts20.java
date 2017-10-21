	public int delete(Class entityClass, Serializable id) throws CreateException {
		try {
			if (get(entityClass, id) != null) {
				getEntityMap(entityClass).remove(id);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new CreateException(e);
		}
	}

	private Object throwInvalidColumnTypeException(
			int i,
			Type type,
			Type returnType) throws HibernateException {
		throw new HibernateException(
				"incompatible column types: " +
						type.getName() +
						", " +
						returnType.getName()
		);
	}

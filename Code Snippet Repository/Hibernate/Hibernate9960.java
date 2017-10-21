	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		IntegerType.INSTANCE.nullSafeSet(
				preparedStatement,
				(value == null ? null : ( (RevisionType) value ).getRepresentation().intValue()),
				index,
				session
		);
	}

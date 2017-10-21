	@Override
	public NamedQueryRepository buildNamedQueryRepository(SessionFactoryImpl sessionFactory) {
		return new NamedQueryRepository(
				namedQueryMap,
				namedNativeQueryMap,
				sqlResultSetMappingMap,
				buildProcedureCallMementos( sessionFactory )
		);

	}

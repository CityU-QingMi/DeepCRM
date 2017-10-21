	@Override
	public NativeQueryImplementor<T> addScalar(String columnAlias, Type type) {
		addReturnBuilder(
				new NativeQueryReturnBuilder() {
					public NativeSQLQueryReturn buildReturn() {
						return new NativeSQLQueryScalarReturn( columnAlias, type );
					}
				}
		);
		return this;
	}
